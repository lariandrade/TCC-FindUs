package com.findus.controller;

import com.findus.models.Prestador;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/visualizarPerfil/{id}")
    public String visualizarPrestador(@PathVariable("id") String email, Model model) {

        Prestador prestador = prestadorRepository.findByUserEmail(email);

        model.addAttribute("prestador", prestador);
        model.addAttribute("email", prestador.getUserEmail());

        return "perfil/perfil-prestador";

    }

    @GetMapping("/prestadorFotoPerfil/{id}")
    public ResponseEntity<byte[]> imagemPerfil(@PathVariable("id") String email, Model model) {
        Prestador prestador = prestadorRepository.findByUserEmail(email);
        if(prestador == null || prestador.getUserFoto() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(prestador.getUserFoto().length);
        return new ResponseEntity<>(prestador.getUserFoto(), headers, HttpStatus.OK);
    }


}
