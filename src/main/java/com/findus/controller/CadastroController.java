package com.findus.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/cadastroCliente")
    public String registarCliente() {
        return "perfil/cliente/registro-cliente";
    }

    @GetMapping("/cadastroPrestador")
    public String registrarPrestador() {
        return "perfil/prestador/registro-prestador";
    }

    @GetMapping("/quiz-result")
    public String resultado(@RequestParam("resultadoRecomendacao") String resultadoQuiz, Model model) {


        model.addAttribute("resultadoQuiz", resultadoQuiz);

        return "quiz/quiz-result";
    }

}

