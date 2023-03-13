package com.findus.controller;

import com.findus.models.Usuario;
import com.findus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/register")
    public ModelAndView registro()
    {
        ModelAndView mv = new ModelAndView("login/register");
        return mv;
    }

    @GetMapping("/register-c")
    public ModelAndView registroCliente()
    {
        ModelAndView mv = new ModelAndView("login/register-c");
        return mv;
    }

    @GetMapping("/register-p")
    public ModelAndView registroPrestador()
    {
        ModelAndView mv = new ModelAndView("login/register-p");
        return mv;
    }

   @PostMapping("/cadastro")
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


}
