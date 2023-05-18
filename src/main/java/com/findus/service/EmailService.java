package com.findus.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private final String emailUsername;

    public EmailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String emailUsername) {
        this.javaMailSender = javaMailSender;
        this.emailUsername = emailUsername;
    }

    @Async
    public void enviarEmail(String destinatario, String assunto, String mensagem) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(emailUsername);
        helper.setTo(destinatario);
        helper.setSubject(assunto);
        helper.setText(mensagem, true); // Defina como true para permitir HTML no corpo do e-mail

        javaMailSender.send(message);
    }
}

