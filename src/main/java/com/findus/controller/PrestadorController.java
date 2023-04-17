package com.findus.controller;

import com.findus.models.Prestador;
import com.findus.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PrestadorController {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @PostMapping("/cadastroPrestador")
    public String criarPrestador(Prestador prestador, MultipartFile file) throws IOException {
        byte[] fotoBytes = file.getBytes();
        prestador.setUserFoto(fotoBytes);
        prestadorRepository.save(prestador);
        return "login/login";
    }

    @GetMapping("adicionar-projeto")
    public String newProject(){
        return "perfil/prestador/publicar-projeto";
    }
}
