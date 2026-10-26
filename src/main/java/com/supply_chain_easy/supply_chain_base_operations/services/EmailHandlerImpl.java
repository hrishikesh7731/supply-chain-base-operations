package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.enums.NotificationPriority;
import com.supply_chain_easy.supply_chain_base_operations.enums.NotificationStatus;
import com.supply_chain_easy.supply_chain_base_operations.models.Notification;
import com.supply_chain_easy.supply_chain_base_operations.models.User;
import com.supply_chain_easy.supply_chain_base_operations.utilites.SystemUtility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmailHandlerImpl implements NotificationHandler{

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailHandlerImpl(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    @Override
    public Notification send(Notification notification) {
        try{
            if(notification.getNotificationPriority().equals(NotificationPriority.URGENT)){

            }else if(notification.getNotificationPriority().equals(NotificationPriority.NORMAL) &&
                    notification.getNotificationStatus().equals(NotificationStatus.IN_QUEUE)){

            }else if(notification.isEmailFailed()){

            }else{
                notification.setNotificationStatus(NotificationStatus.IN_QUEUE);
            }
        }
        catch (Exception e) {
            log.error("Unable to send email for this notification={}", notification, e);
            notification.setNotificationStatus(NotificationStatus.FAILED);
        }
    }

    public void sendEmail(Notification notification) throws MessagingException {

        List<String> toUsers = notification.getToUsers()
                .stream()
                .map(User::getEmail)
                .toList();

        List<String> ccUsers = notification.getCcUsers()
                .stream()
                .map(User::getEmail)
                .toList();

        List<String> bccUsers = notification.getBccUsers()
                .stream()
                .map(User::getEmail)
                .toList();

        String emailBody=notification.getNotificationTemplate().getEmailTemplate();
        String subjectLine=notification.getNotificationTemplate().getSubjectLine();

        emailBody= SystemUtility.replaceTemplateVariableWithValues(emailBody,notification.getNotificationContext().getEmailMap());
        subjectLine=SystemUtility.replaceTemplateVariableWithValues(subjectLine,notification.getNotificationContext().getSubjectLineMap());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setTo(toUsers.toArray(new String[0]));
        mimeMessageHelper.setCc(ccUsers.toArray(new String[0]));
        mimeMessageHelper.setBcc(bccUsers.toArray(new String[0]));
        mimeMessageHelper.setSubject(subjectLine);
        mimeMessageHelper.setText(emailBody, true);

        javaMailSender.send(mimeMessage);
    }
}
