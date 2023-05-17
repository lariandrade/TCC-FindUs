package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.PortfolioRepository;
import com.findus.repository.ClienteRepository;
import com.findus.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @GetMapping("/visualizaPerfilPrestador")
    public String visualizarPrestador(@RequestParam("idCliente") String idCliente, @RequestParam("idPrestador") Long idPrestador, Model model) {

        Prestador prestador = prestadorService.findById(idPrestador);

        Cliente cliente = clienteRepository.findByUserEmail(idCliente);


        List<Portfolio> projetos = portfolioRepository.findByPrestador(prestador);

        model.addAttribute("cliente", cliente);
        model.addAttribute("prestador", prestador);
        model.addAttribute("projetos", projetos);


        return "geral/visualizar-prestador";

    }

}
