package com.findus.controller;

import com.findus.models.AvaliacaoPortfolio;
import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.AvaliarPortfolioRepository;
import com.findus.repository.PortfolioRepository;
import com.findus.service.PortfolioService;
import com.findus.service.PrestadorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class PortfolioController {

    @Autowired
    private PrestadorService prestadorService;


    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private AvaliarPortfolioRepository avaliarPortfolioRepository;


    @Autowired
    private PortfolioRepository portfolioRepository;


    @GetMapping("/pageAdicionarProjeto/{id}")
    public String newProject(@PathVariable("id") String ID, Model model) {
        model.addAttribute("identPrest", ID);
        return "perfil/projetos/publicar-projeto";
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

    @GetMapping("/projetoImagem/{id}")
    public ResponseEntity<byte[]> imagemProjeto(@PathVariable("id") Long idPort) {

        Portfolio portfolio = portfolioService.findById(idPort);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(portfolio.getPortImagem().length);
        return new ResponseEntity<>(portfolio.getPortImagem(), headers, HttpStatus.OK);
    }

    @GetMapping("/visualizarProjeto/{id}")
    public String visualizarProjeto(@PathVariable("id") Long idProjeto, Model model) {

        Portfolio portfolio = portfolioService.findById(idProjeto);

        Prestador prestador = prestadorService.findById(portfolio.getPrestador().getUserID());

        List<AvaliacaoPortfolio> avaliacoes = avaliarPortfolioRepository.findByAvaIdProjeto(idProjeto);
        long totalAvaliacoes = avaliacoes.size();

        model.addAttribute("totalAvaliacoes", totalAvaliacoes);
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("prestador", prestador);
        return "perfil/projetos/visualizar-projeto";
    }

    @GetMapping("/editarProjeto/{id}")
    public String editarProjeto(@PathVariable Long id, Model model){

        Portfolio portfolio = portfolioService.findById(id);

        model.addAttribute("portfolio", portfolio);

        return "perfil/projetos/editar-projeto";
    }

    @PostMapping("/alterarDadosProjeto/{id}")
    public String atualizarPrestador(@PathVariable("id") Long id, @ModelAttribute("portfolio") Portfolio projetoAtualizado, MultipartFile file) throws IOException {

        Portfolio portfolio = portfolioService.findById(id);

        if(!file.isEmpty()){
            byte[] fotoNova = file.getBytes();
            portfolio.setPortImagem(fotoNova);
        }


        portfolio.setPortTituloPrj(projetoAtualizado.getPortTituloPrj());
        portfolio.setPortDescricao(projetoAtualizado.getPortDescricao());
        portfolio.setPortClassificacao(projetoAtualizado.getPortClassificacao());
        portfolio.setPortOrcamento(projetoAtualizado.getPortOrcamento());
        portfolio.setPortSegmento(projetoAtualizado.getPortSegmento());
        portfolio.setPortTempo(projetoAtualizado.getPortTempo());


        portfolioService.save(portfolio);

        return "redirect:/visualizarProjeto/" + portfolio.getPortID();
    }
    @Transactional
    @GetMapping("/excluirProjeto/{id}")
    public String excluirProjeto(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        Portfolio portfolio = portfolioService.findById(id);

        avaliarPortfolioRepository.deleteByPortfolio(portfolio);

        String idPrestador = portfolio.getPrestador().getUserEmail();

        portfolioRepository.deleteById(id);

        redirectAttributes.addAttribute("id", idPrestador);
        return "redirect:/visualizarPerfil/{id}";


    }



}
