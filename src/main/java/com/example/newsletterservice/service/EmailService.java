package com.example.newsletterservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.from:newsletter@example.com}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendHtml(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");
        helper.setText(htmlBody, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(from);
        mailSender.send(msg);
    }
}

