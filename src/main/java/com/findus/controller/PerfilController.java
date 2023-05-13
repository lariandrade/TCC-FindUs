package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.models.Portfolio;
import com.findus.models.Prestador;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PortfolioRepository;
import com.findus.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PerfilController {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/visualizarPerfil/{id}")
    public String visualizarPrestador(@PathVariable("id") String email, Model model) {


        Cliente cliente = clienteRepository.findByUserEmail(email);
        Prestador prestador = prestadorRepository.findByUserEmail(email);

        if (cliente != null) {

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

            return "perfil/prestador/perfil-prestador";

        }


    }


}
