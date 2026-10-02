package com.supply_chain_easy.supply_chain_base_operations.services;
import com.supply_chain_easy.supply_chain_base_operations.constants.SystemConstant;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailNotification(
            String htmlContent,
            String toEmailAddress,
            String subjectLine
    ){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        for(int i = 1; i < SystemConstant.MAIL_RETRY_ATTEMPT; i++){
            try{
                mimeMessageHelper.setTo(toEmailAddress);
                mimeMessageHelper.setSubject(subjectLine);
                mimeMessageHelper.setText(htmlContent, true);
                javaMailSender.send(mimeMessage);
                return;
            }catch (Exception e){
                log.info(String.format("Send email got failed for attempt - %d because of reason: %s", i, e.getMessage()));
                log.info("Retrying again.... ");
            }
        }
        log.info("After maximum retry attempt we failed to send email");

    }

}