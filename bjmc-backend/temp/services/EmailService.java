package com.bjmc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Method to send verification email
    public void sendVerificationEmail(String toEmail, String token) {
        String verificationUrl = "http://localhost:8080/api/visitors/verify-email?token=" + token;
        String subject = "Verify your email";
        String body = "Please click the following link to verify your email: " + verificationUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
