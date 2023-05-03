package com.findus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CadastroController {


    @GetMapping("/cliente")
    public String tipoCliente() {
        return "login/register-client";
    }

    @GetMapping("/prestador")
    public String tipoPrestador() {
        return "login/register-prest";
    }

    @GetMapping("/casdastroCliente")
    public String registarCliente() {
        return "perfil/cliente/registro-cliente";
    }

    @GetMapping("/casdastroPrestador")
    public String registrarPrestador() {
        return "perfil/prestador/registro-prestador";
    }
}

