package com.findus.controller;

import com.findus.models.AvaliacaoPortfolio;
import com.findus.models.Cliente;
import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.AvaliarPortfolioRepository;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PortfolioRepository;
import com.findus.repository.PrestadorRepository;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private AvaliarPortfolioRepository avaliarPortfolioRepository;

    @Autowired
    private AvaliarPortfolioRepository avaliacaoRepository;


    @GetMapping("/visualizaPerfilPrestador")
    public String visualizarPrestador(@RequestParam("idUsuario") String idUser, @RequestParam("idPrestador") Long idPrestador, Model model) {

        Prestador prestador = prestadorService.findById(idPrestador);

        Cliente cliente = clienteRepository.findByUserEmail(idUser);

        if(cliente != null){


            List<Portfolio> projetos = portfolioRepository.findByPrestador(prestador);

            model.addAttribute("cliente", cliente);
            model.addAttribute("prestador", prestador);
            model.addAttribute("projetos", projetos);

            Map<Long, Integer> totalAvaliacoesPorProjeto = new HashMap<>();
            for (Portfolio projeto : projetos) {
                List<AvaliacaoPortfolio> avaliacoes = avaliarPortfolioRepository.findByAvaIdProjeto(projeto.getPortID());
                int totalAvaliacoes = avaliacoes.size();
                totalAvaliacoesPorProjeto.put(projeto.getPortID(), totalAvaliacoes);
            }

            model.addAttribute("totalAvaliacoesPorProjeto", totalAvaliacoesPorProjeto);


            return "geral/visualizar-prestador";

        } else {

            List<Portfolio> projetos = portfolioRepository.findByPrestador(prestador);

            model.addAttribute("prestador", prestador);
            model.addAttribute("projetos", projetos);

            Map<Long, Integer> totalAvaliacoesPorProjeto = new HashMap<>();
            for (Portfolio projeto : projetos) {
                List<AvaliacaoPortfolio> avaliacoes = avaliarPortfolioRepository.findByAvaIdProjeto(projeto.getPortID());
                int totalAvaliacoes = avaliacoes.size();
                totalAvaliacoesPorProjeto.put(projeto.getPortID(), totalAvaliacoes);
            }

            model.addAttribute("totalAvaliacoesPorProjeto", totalAvaliacoesPorProjeto);

            Prestador usuario = prestadorRepository.findByUserEmail(idUser);

            model.addAttribute("usuario", usuario);


            return "geral/visualizar-perfil-prestador";
        }

    }


    @GetMapping("/projeto/{id}")
    public String projetoPrestador(@PathVariable("id") Long idProjeto, @RequestParam("idcliente") Long idCliente, Model model) {

        Portfolio portfolio = portfolioService.findById(idProjeto);

        Prestador prestador = prestadorService.findById(portfolio.getPrestador().getUserID());

        Cliente cliente = clienteService.findById(idCliente);


        List<AvaliacaoPortfolio> avaliacoes = avaliarPortfolioRepository.findByAvaIdProjeto(idProjeto);
        long totalAvaliacoes = avaliacoes.size();

        model.addAttribute("totalAvaliacoes", totalAvaliacoes);

        model.addAttribute("portfolio", portfolio);
        model.addAttribute("prestador", prestador);
        model.addAttribute("cliente", cliente);


        return "geral/visualizar-projeto-prestador";

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


    @GetMapping("/barraPesquisa")
    public String pesquisarServico(Model model, @RequestParam("emailUser") String email, @RequestParam("pesquisa") String pesquisa){


        Cliente cliente = clienteRepository.findByUserEmail(email);

        if (cliente != null) {

            model.addAttribute("nomeUsuario", cliente.getUserNome());
            model.addAttribute("fotoPerfil", "clienteFotoPerfil");
            model.addAttribute("emailUser", cliente.getUserEmail());

            List<Prestador> listaPrestadores = prestadorRepository.findByUserSegmentoIn(Collections.singletonList(pesquisa));

            Map<Prestador, Integer> projetosPorPrestador = new HashMap<>();


            Map<Long, Map<Long, Integer>> avaliacoesPorPrestador = new HashMap<>();

            for (Prestador prest : listaPrestadores) {
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
            model.addAttribute("prestadores", listaPrestadores);
            model.addAttribute("projetosPorPrestador", projetosPorPrestador);

        }

        return "geral/home";

    }

    @GetMapping("/listarTodos")
    public String listarTodos(Model model, @RequestParam("emailUser") String email){

        Cliente cliente = clienteRepository.findByUserEmail(email);

        if (cliente != null) {

            model.addAttribute("nomeUsuario", cliente.getUserNome());
            model.addAttribute("fotoPerfil", "clienteFotoPerfil");
            model.addAttribute("emailUser", cliente.getUserEmail());

            List<Prestador> listaPrestadores = prestadorRepository.findAll();

            Map<Prestador, Integer> projetosPorPrestador = new HashMap<>();


            Map<Long, Map<Long, Integer>> avaliacoesPorPrestador = new HashMap<>();

            for (Prestador prest : listaPrestadores) {
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
            model.addAttribute("prestadores", listaPrestadores);
            model.addAttribute("projetosPorPrestador", projetosPorPrestador);

        }

        return "geral/home";

    }


}
