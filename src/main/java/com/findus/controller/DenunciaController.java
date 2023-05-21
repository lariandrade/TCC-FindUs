package com.findus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DenunciaController {


    @GetMapping("/denunciarCliente/{id}")
    public String denunciarCliente(@PathVariable("id") Long idCliente, Model model) {

        return "geral/denunciar";

    }

    @GetMapping("/denunciarPrestador/{id}")
    public String denunciarPrestador(@PathVariable("id") Long idCliente, Model model) {

        return "geral/denunciar";

    }

}
