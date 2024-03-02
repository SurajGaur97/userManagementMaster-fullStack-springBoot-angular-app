package com.wipro.usermanagement.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.wipro.usermanagement.constants.CommonConstants;
import com.wipro.usermanagement.constants.MailConstants;

/**
 * Config for mail service
 * @author Suraj G
 *
 */
@Configuration
public class MailConfig {
	@Autowired
    private Environment env;
 
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
 
        mailSender.setHost(env.getProperty(MailConstants.SPRING_MAIL_HOST));
        mailSender.setPort(Integer.valueOf(env.getProperty(MailConstants.SPRING_MAIL_PORT)));
        mailSender.setUsername(env.getProperty(MailConstants.SPRING_MAIL_USERNAME));
        mailSender.setPassword(env.getProperty(MailConstants.SPRING_MAIL_PASSWORD));
 
        Properties javaMailProperties = new Properties();
        javaMailProperties.put(MailConstants.MAIL_SMTP_STARTS_ENABLE, CommonConstants.TRUE_TEXT);
        javaMailProperties.put(MailConstants.MAIL_SMTP_AUTH, CommonConstants.TRUE_TEXT);
        javaMailProperties.put(MailConstants.MAIL_TRANSPORT_PROTOCOL, CommonConstants.SMTP);
        javaMailProperties.put(MailConstants.MAIL_DEBUG, CommonConstants.TRUE_TEXT);
 
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
