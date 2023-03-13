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
        ModelAndView mv = new ModelAndView("login/login-c");
        return mv;
    }

    @GetMapping("/login-p")
    public ModelAndView logop()
    {
        ModelAndView mv = new ModelAndView("login/login-p");
        return mv;
    }


}

