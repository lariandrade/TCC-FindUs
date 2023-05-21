package com.findus.controller;

import com.findus.models.AvaliacaoPortfolio;
import com.findus.models.Portfolio;
import com.findus.service.AvaliarPortfolioService;
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



    @GetMapping("/avaliarProjeto/{id}")
    public String avaliaProjeto(@PathVariable("id") Long idProjeto, Model model) {

        Portfolio projeto = portfolioService.findById(idProjeto);

        model.addAttribute("projeto", projeto);

        return "geral/avaliar";

    }


    @PostMapping("/avaliar-projeto")
    public String avaliarProjeto(@RequestParam("rate") int nota, @RequestParam("idProjeto") Long idProjeto) {

        AvaliacaoPortfolio avaliacaoPortfolio = new AvaliacaoPortfolio();

        avaliacaoPortfolio.setAvaIdProjeto(idProjeto);
        avaliacaoPortfolio.setAvaNota(nota);

        avaliarPortfolioService.save(avaliacaoPortfolio);

        return "ok";

    }



}
