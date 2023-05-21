package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.models.Prestador;
import com.findus.service.ClienteService;
import com.findus.service.DenunciaService;
import com.findus.service.PrestadorService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PrestadorService prestadorService;

    @GetMapping("/denunciarPrestador/{id}")
    public String denunciarPrestador(@PathVariable("id") Long idCliente, Model model) {

        return "geral/denunciar";

    }

    @GetMapping("/denunciarCliente")
    public String denunciarCliente(@RequestParam("idprestador") Long idPrestador, @RequestParam("idcliente") Long idCliente, Model model) {

        Prestador denunciado = prestadorService.findById(idPrestador);
        Cliente usuario = clienteService.findById(idCliente);

        model.addAttribute("denunciado", denunciado);
        model.addAttribute("usuario", usuario);

        return "geral/denunciar";
    }


    @PostMapping("/enviar-denuncia-cliente")
    public String enviarDenunciaCliente(@RequestParam("corpo") String motivoDenuncia,
                                 @RequestParam("idUsuario") Long idUsuario,
                                 @RequestParam("idDenunciado") Long idDenunciado) throws MessagingException {


        String assunto = "Nova denuncia.";

        Prestador prestador = prestadorService.findById(idDenunciado);

        Cliente cliente = clienteService.findById(idUsuario);

        String mensagemFormatada = "<html><head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
                + "h3 { color: #333333; }"
                + "p { color: #666666; }"
                + ".button { background-color: #000; border: none; color: white; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer; }"
                + "</style>"
                + "</head><body>"
                + "<h3>Informações do prestador denunciado:</h3>"
                + "<br>"
                + "<p>Nome completo: " + prestador.getUserNome() + "</p>"
                + "<p>CPF/CNPJ: " + prestador.getUserCPF_CNPJ() + "</p>"
                + "<p>Email: " + prestador.getUserEmail() + "</p>"
                + "<br>"
                + "<h3>Reclamação reportada:</h3>"
                + "<p>"+motivoDenuncia+"</p>"
                + "</body></html>";

        denunciaService.enviarDenuncia(mensagemFormatada, idUsuario, idDenunciado, assunto, motivoDenuncia);

        return "redirect:/visualizaPerfilPrestador?idCliente=" + cliente.getUserEmail() + "&idPrestador=" + idDenunciado;

    }

}
