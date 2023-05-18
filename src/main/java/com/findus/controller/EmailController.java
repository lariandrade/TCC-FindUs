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

    @PostMapping("/enviar-email")
    public void enviarEmail(@RequestParam("destinatario") String destinatario, @RequestParam("corpo") String corpo) throws MessagingException {

        String assunto = "Solicitação de Serviço.";

        emailService.enviarEmail(destinatario, assunto, corpo);
    }

}
