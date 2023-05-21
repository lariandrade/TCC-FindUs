package com.findus.service;

import com.findus.models.Denuncia;
import com.findus.repository.DenunciaRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DenunciaService {

    private Date denunData;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private DenunciaRepository denunciaRepository;


    private final String emailUsername;

    public DenunciaService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String emailUsername) {
        this.javaMailSender = javaMailSender;
        this.emailUsername = emailUsername;
    }


    @Async
    public void enviarDenuncia(String mensagem, Long idUsuario, Long idDenunciado, String assunto, String motivoDenuncia) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(emailUsername);
        helper.setTo("denuncias.findus@hotmail.com");
        helper.setSubject(assunto);
        helper.setText(mensagem, true); // Defina como true para permitir HTML no corpo do e-mail

        try {
            javaMailSender.send(message);

            Denuncia denuncia = new Denuncia();

            denuncia.setDenunIdDenunciado(idDenunciado);
            denuncia.setDenunIdUsuario(idUsuario);
            denuncia.setDenunMotivo(motivoDenuncia);
            denuncia.setDenunData(this.denunData = new Date());

            denunciaRepository.save(denuncia);

        } catch (MailException ex) {
            System.err.println("Erro ao enviar o e-mail: " + ex.getMessage());
        }

    }

    public Denuncia save(Denuncia denuncia) {

        return denunciaRepository.save(denuncia);
    }


}
