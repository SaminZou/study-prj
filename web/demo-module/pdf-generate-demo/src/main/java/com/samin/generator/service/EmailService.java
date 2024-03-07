package com.samin.generator.service;

import com.samin.generator.entity.MailProperties;
import com.samin.generator.repository.MailPropertiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final MailPropertiesRepository mailPropertiesRepository;

    public void sendEmail(String email, String content) throws MessagingException {
        MailProperties mailProperties = mailPropertiesRepository.getReferenceById(1);
        JavaMailSenderImpl sender = getMailSender(mailProperties);

        // 简单文本
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom(mailProperties.getUserName());
        mailMessage.setSubject("这是测试邮件");
        mailMessage.setText(content);

        sender.send(mailMessage);
    }

    private JavaMailSenderImpl getMailSender(MailProperties mailProperties) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailProperties.getHost());
        sender.setPort(mailProperties.getPort());
        sender.setDefaultEncoding(mailProperties.getEncoding());
        sender.setUsername(mailProperties.getUserName());
        sender.setPassword(mailProperties.getPassword());
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;
    }
}
