package com.findus.service;

import com.findus.models.ContatoPrestador;
import com.findus.repository.ContatoPrestadorRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ContatoPrestadorService contatoPrestadorService;

    @Autowired
    private ContatoPrestadorRepository contatoPrestadorRepository;


    private final String emailUsername;

    public EmailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String emailUsername) {
        this.javaMailSender = javaMailSender;
        this.emailUsername = emailUsername;
    }

    @Async
    public void enviarEmail(String destinatario, String telefone, Long idCliente, Long idPrestador, String assunto, String mensagemFormatada, String emailPrestador) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(emailUsername);
        helper.setTo(emailPrestador);
        helper.setSubject(assunto);
        helper.setText(mensagemFormatada, true); // Defina como true para permitir HTML no corpo do e-mail

        try {
            javaMailSender.send(message);

            ContatoPrestador contatoPrestador = new ContatoPrestador();

            contatoPrestador.setContTelefone(telefone);
            contatoPrestador.setContEmail(destinatario);
            contatoPrestador.setContIdCliente(idCliente);
            contatoPrestador.setContIdPrestador(idPrestador);

            contatoPrestadorService.save(contatoPrestador);

        } catch (MailException ex) {
            System.err.println("Erro ao enviar o e-mail: " + ex.getMessage());
        }

    }
}

