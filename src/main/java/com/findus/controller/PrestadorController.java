package com.findus.controller;

import com.findus.models.Prestador;
import com.findus.repository.PortfolioRepository;
import com.findus.repository.PrestadorRepository;
import com.findus.service.PrestadorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PrestadorController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private PrestadorRepository prestadorRepository;



    @PostMapping("/cadastroPrestador")
    public String criarPrestador(Prestador prestador, MultipartFile file) throws IOException {
        byte[] fotoBytes = file.getBytes();
        prestador.setUserFoto(fotoBytes);
        prestadorService.save(prestador);
        return "login/login";
    }

    @GetMapping("/prestadorFotoPerfil/{id}")
    public ResponseEntity<byte[]> imagemPerfil(@PathVariable("id") String email, Model model) {
        Prestador prestador = prestadorRepository.findByUserEmail(email);
        if (prestador == null || prestador.getUserFoto() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(prestador.getUserFoto().length);
        return new ResponseEntity<>(prestador.getUserFoto(), headers, HttpStatus.OK);
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
    public String atualizarPrestador(@PathVariable("id") Long id, @ModelAttribute("prestador") Prestador prestadorAtualizado,  MultipartFile file) throws IOException {

        Prestador prestador = prestadorService.findById(id);

        byte[] fotoNova = file.getBytes();
        prestador.setUserFoto(fotoNova);

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
