package com.findus.controller;

import com.findus.models.AvaliacaoPortfolio;
import com.findus.models.Cliente;
import com.findus.models.Portfolio;
import com.findus.service.AvaliarPortfolioService;
import com.findus.service.ClienteService;
import com.findus.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AvaliacaoController {

    @Autowired
    private PortfolioService portfolioService;


    @Autowired
    private AvaliarPortfolioService avaliarPortfolioService;

    @Autowired
    private ClienteService clienteService;



    @GetMapping("/avaliarProjeto/{id}")
    public String avaliaProjeto(@PathVariable("id") Long idProjeto, @RequestParam("userID") Long clienteID, Model model) {

        Portfolio projeto = portfolioService.findById(idProjeto);

        model.addAttribute("projeto", projeto);
        model.addAttribute("userID", clienteID);

        return "geral/avaliar";
    }



    @PostMapping("/avaliar-projeto")
    public String avaliarProjeto(@RequestParam("rate") int nota, @RequestParam("idProjeto") Long idProjeto, @RequestParam("userID") Long idCliente) {

        AvaliacaoPortfolio avaliacaoPortfolio = new AvaliacaoPortfolio();

        Portfolio portfolio = portfolioService.findById(idProjeto);

        avaliacaoPortfolio.setAvaIdProjeto(idProjeto);
        avaliacaoPortfolio.setAvaNota(nota);
        avaliacaoPortfolio.setPortfolio(portfolio);

        avaliarPortfolioService.save(avaliacaoPortfolio);

        Long idPrestador = portfolio.getPrestador().getUserID();

        Cliente cliente = clienteService.findById(idCliente);


       return "redirect:/visualizaPerfilPrestador?idCliente=" + cliente.getUserEmail() + "&idPrestador=" + idPrestador;



    }



}
