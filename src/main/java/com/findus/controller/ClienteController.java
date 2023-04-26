package com.findus.controller;

import com.findus.models.Cliente;
import com.findus.repository.ClienteRepository;
import com.findus.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastroCliente")
    public String criarCliente(Cliente cliente, MultipartFile file) throws IOException {
        byte[] fotoBytes = file.getBytes();
        cliente.setUserFoto(fotoBytes);
        clienteRepository.save(cliente);
        return "login/login";
    }

    @GetMapping("/clienteFotoPerfil/{id}")
    public ResponseEntity<byte[]> imagemPerfil(@PathVariable("id") String email, Model model) {
        Cliente cliente = clienteRepository.findByUserEmail(email);
        if (cliente == null || cliente.getUserFoto() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(cliente.getUserFoto().length);
        return new ResponseEntity<>(cliente.getUserFoto(), headers, HttpStatus.OK);
    }

    @GetMapping("/editarCliente/{id}")
    public String editaPrestador(@PathVariable("id") String prestID, Model model) {

        Long idCliente = Long.parseLong(prestID);
        Cliente cliente = clienteService.findById(idCliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("userID", cliente.getUserID());

        return "perfil/cliente/editar-cliente";

    }




}
