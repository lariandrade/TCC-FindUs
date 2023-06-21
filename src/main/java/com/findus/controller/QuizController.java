package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.repository.ClienteRepository;
import com.findus.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/quiz-result")
    public String resultado(@RequestParam("resultadoRecomendacao") String resultadoQuiz, @RequestParam("idCliente") Long id, Model model) {

        model.addAttribute("resultadoQuiz", resultadoQuiz);
        model.addAttribute("idCliente", id);

        return "quiz/quiz-result";
    }

    @PostMapping("/cadastraServicoDesejado")
    public String cadastrarServico(@RequestParam("jobs") List<String> jobs, @RequestParam("idCliente") Long idCliente, RedirectAttributes redirectAttributes) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);

        if (cliente != null) {

            cliente.getObjetivo().clear();

            for (int i = 0; i < jobs.size(); i++) {
                String job = jobs.get(i);
                job = job.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "");
                jobs.set(i, job);
            }


            cliente.getObjetivo().addAll(jobs); // Adicionar a lista de elementos à coleção existente

            clienteRepository.save(cliente); // Salvar o objeto Cliente atualizado no banco de dados
        }

        redirectAttributes.addAttribute("userEmail", cliente.getUserEmail());
        redirectAttributes.addAttribute("userSenha", cliente.getUserSenha());

        return "redirect:/home";
    }

    @GetMapping("/refazerQuiz")
    public String refazerQuiz(@RequestParam("idCliente") Long id, Model model) {


        Cliente cliente = clienteService.findById(id);

        model.addAttribute("cliente", cliente);

        return "quiz/quiz";


    }



}
