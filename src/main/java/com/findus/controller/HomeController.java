package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PortfolioRepository;
import com.findus.service.ClienteService;
import com.findus.service.PortfolioService;
import com.findus.service.PrestadorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PrestadorService prestadorService;


    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @GetMapping("/visualizaPerfilPrestador")
    public String visualizarPrestador(@RequestParam("idCliente") String idCliente, @RequestParam("idPrestador") Long idPrestador, Model model) {

        Prestador prestador = prestadorService.findById(idPrestador);

        Cliente cliente = clienteRepository.findByUserEmail(idCliente);


        List<Portfolio> projetos = portfolioRepository.findByPrestador(prestador);

        model.addAttribute("cliente", cliente);
        model.addAttribute("prestador", prestador);
        model.addAttribute("projetos", projetos);


        return "geral/visualizar-prestador";

    }


    @GetMapping("/projeto/{id}")
    public String projetoPrestador(@PathVariable("id") Long idProjeto, @RequestParam("idcliente") Long idCliente, Model model) {

        Portfolio portfolio = portfolioService.findById(idProjeto);

        Prestador prestador = prestadorService.findById(portfolio.getPrestador().getUserID());

        Cliente cliente = clienteService.findById(idCliente);

        model.addAttribute("portfolio", portfolio);
        model.addAttribute("prestador", prestador);
        model.addAttribute("cliente", cliente);

        return "geral/visualizar-projeto-prestador";

    }

    @GetMapping("/avaliarProjeto/{id}")
    public String avaliaProjeto(@PathVariable("id") Long idProjeto, Model model) {

        Portfolio projeto = portfolioService.findById(idProjeto);


        model.addAttribute("projeto", projeto);

        return "geral/avaliar";

    }

    @GetMapping("/denunciarUsuario/{id}")
    public String denunciaUsuario(@PathVariable("id") Long idProjeto, Model model) {

        Portfolio projeto = portfolioService.findById(idProjeto);


        model.addAttribute("projeto", projeto);

        return "geral/denunciar";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalida a sessão atual do usuário
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redireciona para a página de login
        return "redirect:/login";
    }


}
