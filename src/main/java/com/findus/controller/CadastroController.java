package com.findus.controller;

import org.springframework.stereotype.Controller;
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

    @GetMapping("/quiz")
    public String perguntas() {
        return "quiz/quiz";
    }

    @GetMapping("/quiz-result")
    public String resultado(@RequestParam("resultadoRecomendacao") String resultadoQuiz) {
        System.out.println(resultadoQuiz);

        return "quiz/quiz-result";
    }

}

