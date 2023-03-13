package com.findus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroController {



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


}
