package com.supply_chain_easy.supply_chain_base_operations.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class SystemConfiguration {

    public String apiEmailAddress = "6206472735rahul@gmail.com";

    public String apiEmailPassword = "ceffelhhjfderwei";

    @Bean
    public JavaMailSender createJavaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(mailProperties);
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(apiEmailAddress);
        javaMailSender.setPassword(apiEmailPassword);
        return javaMailSender;
    }
}
