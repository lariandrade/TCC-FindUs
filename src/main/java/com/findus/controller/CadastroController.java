package com.findus.controller;

import com.findus.models.Prestador;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class CadastroController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @GetMapping("/register")
    public ModelAndView registro()
    {
        ModelAndView mv = new ModelAndView("login/register");
        return mv;
    }

    @GetMapping("/cadastrarCliente")
    public ModelAndView registroCliente()
    {
        ModelAndView mv = new ModelAndView("login/registro-cliente");
        return mv;
    }

    @GetMapping("/cadastrarPrestador")
    public ModelAndView registroPrestador()
    {
        ModelAndView mv = new ModelAndView("login/registro-prestador");
        return mv;
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/cadastroCliente")
    public String criarCliente(Cliente cliente, @RequestParam("userFoto") MultipartFile foto) throws IOException {
        cliente.setUserFoto(foto.getBytes());
        clienteRepository.save(cliente);
        return "login/login";
    }*/


    @RequestMapping(method = RequestMethod.POST, value = "/cadastroPrestador")
    public String criarPrestador(Prestador prestador, MultipartFile file) throws IOException {
        byte[] fotoBytes = file.getBytes();
        prestador.setUserFoto(fotoBytes);
        prestadorRepository.save(prestador);
        return "login/login";
    }

}

