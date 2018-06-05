package com.hms.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Session;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        //USING GMAIL
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("abc@gmail.com");
        mailSender.setPassword("abc123");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(javaMailProperties,
                new javax.mail.Authenticator() {
                  protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                      return new javax.mail.PasswordAuthentication("abc@gmail.com", "abc123");
                }
              });
        mailSender.setJavaMailProperties(javaMailProperties);
        mailSender.setSession(session);
        return mailSender;
    }
}