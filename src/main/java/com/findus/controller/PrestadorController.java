package com.findus.controller;

import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.PortfolioRepository;
import com.findus.repository.PrestadorRepository;
import com.findus.service.PrestadorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class PrestadorController {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PrestadorService prestadorService;

    @PostMapping("/cadastroPrestador")
    public String criarPrestador(Prestador prestador, MultipartFile file) throws IOException {
        byte[] fotoBytes = file.getBytes();
        prestador.setUserFoto(fotoBytes);
        prestadorService.save(prestador);
        return "login/login";
    }

    @GetMapping("/adicionar-projeto/{id}")
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


    @GetMapping("/editarPrestador/{id}")
    public String editaPrestador(@PathVariable("id") String prestID, Model model) {

        Long idPrest = Long.parseLong(prestID);
        Prestador prestador = prestadorService.findById(idPrest);

        model.addAttribute("prestador", prestador);
        /*model.addAttribute("email", prestador.getUserEmail());*/
        model.addAttribute("userID", prestador.getUserID());

        return "perfil/prestador/editar-prestador";

    }

    @PostMapping("/alterarDadosPrestador/{id}")
    public String atualizarPrestador(@PathVariable("id") Long id, @ModelAttribute("prestador") Prestador prestadorAtualizado) {
        Prestador prestador = prestadorService.findById(id);

        prestador.setUserNome(prestadorAtualizado.getUserNome());
        prestador.setUserTelefone(prestadorAtualizado.getUserTelefone());
        prestador.setUserEmail(prestadorAtualizado.getUserEmail());
        prestador.setUserSenha(prestadorAtualizado.getUserSenha());
        prestador.setUserSegmento(prestadorAtualizado.getUserSegmento());
        prestador.setUserDescricao(prestadorAtualizado.getUserDescricao());
        prestadorService.save(prestador);
        return "redirect:/visualizarPerfil/" + prestadorAtualizado.getUserEmail();
    }

    @GetMapping("/pageDeletarPrestador/{id}")
    public String pageDeletaPrestador(@PathVariable("id") String prestID, Model model) {

        Long idPrest = Long.parseLong(prestID);
        Prestador prestador = prestadorService.findById(idPrest);

        model.addAttribute("prestador", prestador);
        model.addAttribute("userID", prestador.getUserID());

        return "perfil/prestador/deletar-prestador";

    }

    @Transactional
    @GetMapping("/deletaPrestador/{id}")
    public String excluirPrestador(@PathVariable("id") String prestID) {

        Long idPrest = Long.parseLong(prestID);

        Prestador prestador = prestadorService.findById(idPrest);

        portfolioRepository.deleteByPrestador(prestador);
        prestadorService.deleteById(idPrest);

        return "redirect:/login";


    }



}
