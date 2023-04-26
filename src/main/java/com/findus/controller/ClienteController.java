package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastroCliente")
    public String criarCliente(Cliente cliente, MultipartFile file) throws IOException {
        byte[] fotoBytes = file.getBytes();
        cliente.setUserFoto(fotoBytes);
        clienteRepository.save(cliente);
        return "login/login";
    }




}
