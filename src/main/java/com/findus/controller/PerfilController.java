package com.findus.controller;

import com.findus.models.*;
import com.findus.repository.AvaliarPortfolioRepository;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PortfolioRepository;
import com.findus.repository.PrestadorRepository;
import com.findus.service.ContatoPrestadorService;
import com.findus.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PerfilController {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private PrestadorService prestadorService;


    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContatoPrestadorService contatoPrestadorService;


    @Autowired
    private AvaliarPortfolioRepository avaliacaoRepository;


    @GetMapping("/visualizarPerfil/{id}")
    public String visualizarPrestador(@PathVariable("id") String email, Model model) {


        Cliente cliente = clienteRepository.findByUserEmail(email);
        Prestador prestador = prestadorRepository.findByUserEmail(email);

        if (cliente != null) {

            ContatoPrestador contatoPrestador = contatoPrestadorService.findById(cliente.getUserID());

            if(contatoPrestador != null){
                Prestador prestadorContatado = prestadorService.findById(contatoPrestador.getContIdPrestador());
                model.addAttribute("prestadorContatado", prestadorContatado);
            }

            model.addAttribute("cliente", cliente);
            model.addAttribute("email", cliente.getUserEmail());
            model.addAttribute("userID", cliente.getUserID());

            List<String> objetivos = cliente.getObjetivo();

            // Adicione a lista ao modelo
            model.addAttribute("objetivos", objetivos);




            return "perfil/cliente/perfil-cliente";

        } else {

            model.addAttribute("prestador", prestador);
            model.addAttribute("email", prestador.getUserEmail());
            model.addAttribute("userID", prestador.getUserID());

            List<Portfolio> projetos = portfolioRepository.findByPrestador(prestador);

            model.addAttribute("projetos", projetos);

            Map<Long, Integer> totalAvaliacoesPorProjeto = new HashMap<>();
            for (Portfolio projeto : projetos) {
                List<AvaliacaoPortfolio> avaliacoes = avaliacaoRepository.findByAvaIdProjeto(projeto.getPortID());
                int totalAvaliacoes = avaliacoes.size();
                totalAvaliacoesPorProjeto.put(projeto.getPortID(), totalAvaliacoes);
            }

            model.addAttribute("totalAvaliacoesPorProjeto", totalAvaliacoesPorProjeto);

            System.out.println(totalAvaliacoesPorProjeto);

            return "perfil/prestador/perfil-prestador";

        }


    }


}
