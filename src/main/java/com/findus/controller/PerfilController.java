package com.findus.controller;

import com.findus.repository.ClienteRepository;
import com.findus.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PerfilController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

   /* @GetMapping("/editarPerfil/{id_Usuario}")
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

    }*/

}
