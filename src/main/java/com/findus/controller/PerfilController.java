package com.findus.controller;

import com.findus.models.Usuario;
import com.findus.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PerfilController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/editarPerfil/{id_Usuario}")
    public ModelAndView getEdit(@PathVariable("id_Usuario") Long id_Usuario) {

        Usuario resul = usuarioRepository.findById(id_Usuario).get();

        String tipoUsuario = resul.getUserTipo();

        if(tipoUsuario.equals("prestador")){
            ModelAndView mv = new ModelAndView("perfil/perfilPrestador");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("perfil/perfilCliente");
            return mv;
        }

    }

}
