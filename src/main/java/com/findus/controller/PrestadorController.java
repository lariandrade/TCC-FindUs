package com.findus.controller;

import com.findus.exception.UsuarioException;
import com.findus.models.ContatoPrestador;
import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.AvaliarPortfolioRepository;
import com.findus.repository.ContatoPrestadorRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class PrestadorController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private PrestadorRepository prestadorRepository;


    @Autowired
    private AvaliarPortfolioRepository avaliarPortfolioRepository;

    @Autowired
    private ContatoPrestadorRepository contatoPrestadorRepository;




    @PostMapping("/cadastrarPrestador")
    public String criarPrestador(Prestador prestador, MultipartFile file, RedirectAttributes redirectAttributes, Model model) throws IOException {
        try {
            byte[] fotoBytes = file.getBytes();
            prestador.setUserFoto(fotoBytes);
            prestadorService.save(prestador);

            //model.addAttribute("cliente", cliente);

            redirectAttributes.addAttribute("userEmail", prestador.getUserEmail());
            redirectAttributes.addAttribute("userSenha", prestador.getUserSenha());

            return "redirect:/home";

        } catch (UsuarioException e) {
            model.addAttribute("error", e.getMessage());
            return "perfil/prestador/registro-prestador";
        }
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

        if(!file.isEmpty()){
            byte[] fotoNova = file.getBytes();
            prestador.setUserFoto(fotoNova);
        }


        prestador.setUserNome(prestadorAtualizado.getUserNome());
        prestador.setUserTelefone(prestadorAtualizado.getUserTelefone());
        prestador.setUserEmail(prestadorAtualizado.getUserEmail());
        prestador.setUserSenha(prestadorAtualizado.getUserSenha());
        prestador.setUserSegmento(prestadorAtualizado.getUserSegmento());
        prestador.setUserDescricao(prestadorAtualizado.getUserDescricao());

        prestadorService.update(prestador);

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


        //filtra todos os contatos feitos ao prestador
        List<ContatoPrestador> contatos = contatoPrestadorRepository.findByContIdPrestador(idPrest);

        //deleta todos os contatos
        for (ContatoPrestador contato : contatos) {
            contatoPrestadorRepository.delete(contato);
        }

        //filtra o prestador por ID
        Prestador prestador = prestadorService.findById(idPrest);

        //lista todos os projetos do prestador
        List<Portfolio> portfolios = prestador.getProjetos();

        //deleta todas as avaliações dos projetos do prestador
        for (Portfolio portfolio : portfolios) {
            avaliarPortfolioRepository.deleteByPortfolio(portfolio);
        }

        //deleta todos os projetos do prestador
        portfolioRepository.deleteByPrestador(prestador);
        //deleta o prestador
        prestadorService.deleteById(idPrest);

        return "redirect:/login";

    }



}
