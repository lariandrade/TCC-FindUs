package com.findus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController{

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/login-c")
    public ModelAndView logoc()
    {
        ModelAndView mv = new ModelAndView("login-c");
        return mv;
    }

    @GetMapping("/login-p")
    public ModelAndView logop()
    {
        ModelAndView mv = new ModelAndView("login-p");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView registro()
    {
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }


    @GetMapping("/register-c")
    public ModelAndView registroCliente()
    {
        ModelAndView mv = new ModelAndView("register-c");
        return mv;
    }


    @GetMapping("/register-p")
    public ModelAndView registroPrestador()
    {
        ModelAndView mv = new ModelAndView("register-p");
        return mv;
    }

}

