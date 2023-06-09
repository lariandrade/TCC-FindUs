package com.findus.controller;

import com.findus.models.AvaliacaoPortfolio;
import com.findus.models.Cliente;
import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AvaliarPortfolioRepository avaliacaoRepository;


    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login/login");
        return mv;
    }

    @GetMapping("/home")
    public String autenticarUsuario(Model model, @RequestParam("userEmail") String email, @RequestParam("userSenha") String senha) {

        Cliente cliente = clienteRepository.findByUserEmailAndUserSenha(email, senha);
        Prestador prestador = prestadorRepository.findByUserEmailAndUserSenha(email, senha);

        String tipoUsuario;

        if (cliente != null) {

            model.addAttribute("nomeUsuario", cliente.getUserNome());
            model.addAttribute("fotoPerfil", "clienteFotoPerfil");
            model.addAttribute("emailUser", cliente.getUserEmail());

            List<Prestador> prestadores = prestadorRepository.findByUserSegmentoIn(cliente.getObjetivo());

            Map<Prestador, Integer> projetosPorPrestador = new HashMap<>();


           /* for (Prestador prest : prestadores) {

                List<Portfolio> projetos = portfolioRepository.findByPrestador(prest);
                int totalProjetos = projetos.size();
                projetosPorPrestador.put(prest, totalProjetos);


            }*/


            Map<Long, Map<Long, Integer>> avaliacoesPorPrestador = new HashMap<>();

            for (Prestador prest : prestadores) {
                List<Portfolio> portfolios = portfolioRepository.findByPrestador(prest);
                Map<Long, Integer> avaliacoesPorPortfolio = new HashMap<>();

                int somaAvaliacoes = 0; // Variável para armazenar a soma das avaliações por prestador

                for (Portfolio portfolio : portfolios) {
                    List<AvaliacaoPortfolio> avaliacoes = avaliacaoRepository.findByPortfolio(portfolio);
                    int totalNotas = 0;

                    if (avaliacoes != null && !avaliacoes.isEmpty()) {
                        for (AvaliacaoPortfolio avaliacao : avaliacoes) {
                            totalNotas += avaliacao.getAvaNota();
                        }
                        somaAvaliacoes += totalNotas; // Atualiza a soma das avaliações
                    }

                    avaliacoesPorPortfolio.put(portfolio.getPortID(), totalNotas);
                }

                avaliacoesPorPrestador.put(prest.getUserID(), avaliacoesPorPortfolio);
                projetosPorPrestador.put(prest, portfolios.size());

                // Armazena a soma das avaliações no modelo
                model.addAttribute("somaAvaliacoes", somaAvaliacoes);
            }

            model.addAttribute("avaliacoesPorPrestador", avaliacoesPorPrestador);
            model.addAttribute("prestadores", prestadores);
            model.addAttribute("projetosPorPrestador", projetosPorPrestador);

            return "geral/home";


        } else if (prestador != null) {
            model.addAttribute("nomeUsuario", prestador.getUserNome());
            model.addAttribute("fotoPerfil", "prestadorFotoPerfil");
            model.addAttribute("emailUser", prestador.getUserEmail());
            return "geral/home";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos.");
            return "login/login";
        }

    }



    @GetMapping("/remember")
    public ModelAndView remember() {
        ModelAndView mv = new ModelAndView("login/forget-password");
        return mv;
    }

    @PostMapping("/reset")
    public String reset(Model model, @RequestParam("userEmail") String email, @RequestParam("userSenha") String senha) {
        Cliente cliente = clienteRepository.findByUserEmail(email);
        Prestador prestador = prestadorRepository.findByUserEmail(email);

        if (cliente != null) {
            cliente.setUserSenha(senha);
            this.clienteRepository.save(cliente);
            return "login/login";
        }

        if (prestador != null) {
            prestador.setUserSenha(senha);
            this.prestadorRepository.save(prestador);
            return "login/login";
        } else {
            model.addAttribute("error", "E-mail não cadastrado.");
            return "login/forget-password";
        }


    }
}

