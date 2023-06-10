package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.models.ContatoPrestador;
import com.findus.models.Prestador;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PortfolioRepository;
import com.findus.repository.PrestadorRepository;
import com.findus.service.ClienteService;
import com.findus.service.ContatoPrestadorService;
import com.findus.service.EmailService;
import com.findus.service.PrestadorService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmailController {


    @Autowired
    private EmailService emailService;

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContatoPrestadorService contatoPrestadorService;

    @GetMapping("/contatarPrestador")
    public String contatoPrestador(@RequestParam("idprestador") Long idPrestador, @RequestParam("idcliente") Long idCliente, Model model) {

        Prestador prestador = prestadorService.findById(idPrestador);
        Cliente cliente = clienteService.findById(idCliente);

        model.addAttribute("prestador", prestador);
        model.addAttribute("cliente", cliente);

        return "geral/contato";
    }

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar-email")
    public String enviarEmail(@RequestParam("emailCliente") String emailCliente,
                              @RequestParam("telefoneCliente") String telefone,
                              @RequestParam("corpo") String corpo,
                              @RequestParam("idCliente") Long idCliente,
                              @RequestParam("idPrestador") Long idPrestador,
                              @RequestParam("nomePrestador") String nomePrestador,
                              @RequestParam("nomeCliente") String nomeCliente) throws MessagingException {

        Prestador prestador = prestadorService.findById(idPrestador);

        String assunto = "Você tem uma nova solicitação de serviço.";


        String mensagemFormatada = "<html><head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
                + "h3 { color: #333333; }"
                + "p { color: #666666; }"
                + ".button { background-color: #000; border: none; color: white; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer; }"
                + "</style>"
                + "</head><body>"
                + "<h3>Olá, " + nomePrestador + "!</h3>"
                + "<p>Você tem uma nova mensagem de " + nomeCliente + ":</p>"
                + "<p style=\"font-style: italic;\">" + corpo + "</p>"
                + "<p style=\"font-weight: bold;\">Informações do cliente para contato:</p>"
                + "<p>Email: " + emailCliente + "</p>"
                + "<p>Telefone: " + telefone + "</p>"
                + "<a href=\"http://localhost:8090/visualizarCliente?idCliente="+idCliente+"&idPrestador="+idPrestador+"\">"
                + "<button class=\"button\">Visualizar Cliente</button>"
                + "</a></body></html>";

        emailService.enviarEmail(emailCliente, telefone, idCliente, idPrestador, assunto, mensagemFormatada, prestador.getUserEmail());

        return "redirect:/visualizaPerfilPrestador?idCliente=" + emailCliente + "&idPrestador=" + idPrestador;

    }

    @GetMapping("/visualizarCliente")
    public String visualizarCliente(@RequestParam("idCliente") Long idCliente, @RequestParam("idPrestador") Long idPrestador, Model model) {


        Cliente cliente = clienteService.findById(idCliente);
        Prestador prestador = prestadorService.findById(idPrestador);

        if (cliente != null) {

            ContatoPrestador contatoPrestador = contatoPrestadorService.findById(cliente.getUserID());

            if (contatoPrestador != null) {
                Prestador prestadorContatado = prestadorService.findById(contatoPrestador.getContIdPrestador());
                model.addAttribute("prestadorContatado", prestadorContatado);
            }

            model.addAttribute("cliente", cliente);
            model.addAttribute("prestador", prestador);
            model.addAttribute("email", prestador.getUserEmail());
            model.addAttribute("userID", cliente.getUserID());

            List<String> objetivos = cliente.getObjetivo();

            // Adicione a lista ao modelo
            model.addAttribute("objetivos", objetivos);

            return "geral/visualizar-cliente";

        }
        return "não encontrou";
    }
}