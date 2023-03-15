package com.findus.controller;

import com.findus.models.Usuario;
import com.findus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @GetMapping("/login")
    public ModelAndView login()
    {
        ModelAndView mv = new ModelAndView("login/login");
        return mv;
    }
    @PostMapping("/entrar")
    public String entrar(Model model, Usuario user)
    {
        Usuario userVerif = this.usuarioRepository.Login(user.getUserSenha(), user.getUserEmail());
        if(userVerif != null){
            return "/home";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos.");
            return "login/login";
        }

    }

    @GetMapping("/remember")
    public ModelAndView remember()
    {
        ModelAndView mv = new ModelAndView("login/reset-senha");
        return mv;
    }


}

