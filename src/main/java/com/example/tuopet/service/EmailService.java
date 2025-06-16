package com.example.tuopet.service;

import com.example.tuopet.dto.EmailDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(EmailDetailsDTO emailDetails) {
        var message = new SimpleMailMessage();

        message.setFrom(sender);
        message.setTo(emailDetails.getPara());
        message.setSubject(emailDetails.getAssunto());
        message.setText(emailDetails.getCorpo());

        javaMailSender.send(message);
    }
}
