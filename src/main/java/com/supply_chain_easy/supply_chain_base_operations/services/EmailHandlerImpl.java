
package com.supply_chain_easy.supply_chain_base_operations.services;

import com.supply_chain_easy.supply_chain_base_operations.enums.NotificationPriority;
import com.supply_chain_easy.supply_chain_base_operations.enums.NotificationStatus;
import com.supply_chain_easy.supply_chain_base_operations.models.Notification;
import com.supply_chain_easy.supply_chain_base_operations.models.User;
import com.supply_chain_easy.supply_chain_base_operations.repositories.NotificationRepository;
import com.supply_chain_easy.supply_chain_base_operations.utilites.SystemUtility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmailHandlerImpl implements NotificationHandler {

    private final JavaMailSender javaMailSender;
    private final NotificationRepository notificationRepository;

    @Autowired
    public EmailHandlerImpl(JavaMailSender javaMailSender,
                            NotificationRepository notificationRepository) {
        this.javaMailSender = javaMailSender;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification send(Notification notification) {

        log.info("Processing notification. ID: {}, Priority: {}, Status: {}",
                notification.getSysId(),
                notification.getNotificationPriority(),
                notification.getNotificationStatus());

        try {
            if (notification.getNotificationPriority().equals(NotificationPriority.URGENT)) {

                log.info("Sending urgent notification. ID: {}", notification.getSysId());

                this.sendEmail(notification);
                notification.setEmailDelivered(true);
                notification.setEmailFailed(false);

            } else if (notification.getNotificationPriority().equals(NotificationPriority.NORMAL)
                    && notification.getNotificationStatus().equals(NotificationStatus.IN_QUEUE)) {

                log.info("Sending queued normal notification. ID: {}", notification.getSysId());

                this.sendEmail(notification);
                notification.setEmailDelivered(true);
                notification.setEmailFailed(false);

            } else if (notification.isEmailFailed()) {

                log.warn("Email previously failed. Re-queuing notification. ID: {}",
                        notification.getSysId());

                notification.setNotificationStatus(NotificationStatus.IN_QUEUE);
                notification.setEmailFailed(true);

            } else {

                log.info("Notification not eligible for sending. ID: {}",
                        notification.getSysId());

                notification.setNotificationStatus(NotificationStatus.IN_QUEUE);
            }

        } catch (Exception e) {

            log.error("Email delivery failed. Notification ID: {}. Reason: {}",
                    notification.getSysId(), e.getMessage(), e);

            notification.setFailureMessage(
                    notification.getFailureMessage() + " [EMAIL] " + e.getMessage());

            notification.setEmailFailed(true);
            notification.setNotificationStatus(NotificationStatus.FAILED);
        }
        notification.setUpdatedAt(LocalDateTime.now());
        notification.setUpdatedBy("system");
        notificationRepository.save(notification);

        log.info("Notification persisted. ID: {}, Final status: {}, Email failed: {}",
                notification.getSysId(),
                notification.getNotificationStatus(),
                notification.isEmailFailed());

        return notification;
    }

    public void sendEmail(Notification notification) throws MessagingException {

        log.debug("Preparing email. Notification ID: {}", notification.getSysId());

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

        log.debug("Email recipients prepared. To: {}, CC: {}, BCC: {}",
                toUsers.size(), ccUsers.size(), bccUsers.size());

        String emailBody = notification.getNotificationTemplate().getEmailTemplate();
        String subjectLine = notification.getNotificationTemplate().getSubjectLine();

        emailBody = SystemUtility.replaceTemplateVariableWithValues(
                emailBody,
                notification.getNotificationContext().getEmailMap());

        subjectLine = SystemUtility.replaceTemplateVariableWithValues(
                subjectLine,
                notification.getNotificationContext().getSubjectLineMap());

        log.debug("Email template variables replaced. Notification ID: {}",
                notification.getSysId());


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(toUsers.toArray(new String[0]));
        mimeMessageHelper.setCc(ccUsers.toArray(new String[0]));
        mimeMessageHelper.setBcc(bccUsers.toArray(new String[0]));
        mimeMessageHelper.setSubject(subjectLine);
        mimeMessageHelper.setText(emailBody, true);

        log.info("Attempting email delivery. Notification ID: {}",
                notification.getSysId());

        RetryService.retry(() -> javaMailSender.send(mimeMessage));

        log.info("Email delivered successfully. Notification ID: {}",
                notification.getSysId());
    }
}
