package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.models.Prestador;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PrestadorRepository;
import com.findus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
            model.addAttribute("prestadores", prestadores);

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

