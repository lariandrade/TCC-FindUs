package com.findus.controller;

import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.PortfolioRepository;
import com.findus.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class PortfolioController {

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private PortfolioRepository portfolioRepository;


    @GetMapping("/pageAdicionarProjeto/{id}")
    public String newProject(@PathVariable("id") String ID, Model model) {
        model.addAttribute("identPrest", ID);
        return "perfil/prestador/publicar-projeto";
    }

    @PostMapping("/cadastrarProjeto")
    public String criaProjeto(Portfolio portfolio, MultipartFile foto, @RequestParam("prestID") String prestID, RedirectAttributes redirectAttributes) throws IOException {
        Long idPrest = Long.parseLong(prestID);
        Prestador prestador = prestadorService.findById(idPrest);
        portfolio.setPrestador(prestador);

        byte[] imagemByte = foto.getBytes();
        portfolio.setPortImagem(imagemByte);

        portfolioRepository.save(portfolio);

        redirectAttributes.addAttribute("id", prestador.getUserEmail());
        return "redirect:/visualizarPerfil/{id}";
    }

}
