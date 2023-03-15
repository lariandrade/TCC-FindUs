package com.findus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

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

    @GetMapping("/remember")
    public ModelAndView remember()
    {
        ModelAndView mv = new ModelAndView("login/reset-senha");
        return mv;
    }


}

