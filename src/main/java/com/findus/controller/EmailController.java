package com.findus.controller;

import com.findus.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {


    @Autowired
    private EmailService emailService;

    @GetMapping("contatarPrestador")
    public String contatoPrestador() {

        return "geral/contato";
    }

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar-email")
    public void enviarEmail(@RequestParam("destinatario") String destinatario, @RequestParam("corpo") String corpo) throws MessagingException {

        String assunto = "Você tem uma nova solicitação de serviço.";



        String mensagem = "<html><head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
                + "h1 { color: #333333; }"
                + "p { color: #666666; }"
                + ".button { background-color: #4CAF50; border: none; color: white; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer; }"
                + "</style>"
                + "</head><body>"
                + "<h1>Olá!</h1>"
                + "<p>Este é um e-mail de teste com estilos CSS.</p>"
                + "<p>Clique no botão abaixo:</p>"
                + "<a href=\"http://localhost:8090/\"><button class=\"button\">Visualizar Cliente</button></a>"
                + corpo + "</body></html>";


        emailService.enviarEmail(destinatario, assunto, mensagem);

    }

}
