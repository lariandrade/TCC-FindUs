package com.findus.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(String destinatario, String assunto, String corpo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("contato.findus@hotmail.com"); //precisa definir pois está dando erro de acesso
        helper.setTo(destinatario);
        helper.setSubject(assunto);
        helper.setText(corpo);

        javaMailSender.send(message);
    }
}
