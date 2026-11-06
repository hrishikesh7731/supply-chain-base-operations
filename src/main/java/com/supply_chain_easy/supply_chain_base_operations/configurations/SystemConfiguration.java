package com.supply_chain_easy.supply_chain_base_operations.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class SystemConfiguration {

    @Value("${MAIL_USERNAME}")
    private String apiEmailAddress;

    @Value("${MAIL_PASSWORD}")
    private String apiEmailPassword;

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

    @Bean
    public ExecutorService createExecutorService(){
        return Executors.newFixedThreadPool(10);
    }
}
