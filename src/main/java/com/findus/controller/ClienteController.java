package com.findus.controller;

import com.findus.exception.ClienteExistenteException;
import com.findus.models.Cliente;
import com.findus.repository.ClienteRepository;
import com.findus.service.ClienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/cadastrarCliente")
    public String criarCliente(Cliente cliente, MultipartFile file, Model model) throws IOException {

        try {
            byte[] fotoBytes = file.getBytes();
            cliente.setUserFoto(fotoBytes);
            clienteService.save(cliente);

            model.addAttribute("cliente", cliente);

            return "quiz/quiz";

        } catch (ClienteExistenteException e) {
            model.addAttribute("error", e.getMessage());
            return "perfil/cliente/registro-cliente";
        }
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
    public String editarCliente(@PathVariable("id") String prestID, Model model) {

        Long idCliente = Long.parseLong(prestID);
        Cliente cliente = clienteService.findById(idCliente);

        model.addAttribute("cliente", cliente);
        /*model.addAttribute("email", prestador.getUserEmail());*/
        model.addAttribute("userID", cliente.getUserID());

        return "perfil/cliente/editar-cliente";

    }

    @PostMapping("/alterarDadosCliente/{id}")
    public String atualizarPrestador(@PathVariable("id") Long id, @ModelAttribute("cliente") Cliente clienteAtualizado, MultipartFile file) throws IOException {

        Cliente cliente = clienteService.findById(id);

        if (!file.isEmpty()) {
            byte[] fotoNova = file.getBytes();
            cliente.setUserFoto(fotoNova);
        }


        cliente.setUserNome(clienteAtualizado.getUserNome());
        cliente.setUserTelefone(clienteAtualizado.getUserTelefone());
        cliente.setUserEmail(clienteAtualizado.getUserEmail());
        cliente.setUserSenha(clienteAtualizado.getUserSenha());
        cliente.setUserSegmento(clienteAtualizado.getUserSegmento());
        cliente.setUserDescricao(clienteAtualizado.getUserDescricao());

        clienteService.update(cliente);

        return "redirect:/visualizarPerfil/" + clienteAtualizado.getUserEmail();
    }


    @GetMapping("/pageDeletarCliente/{id}")
    public String pageDeletaCliente(@PathVariable("id") String cliID, Model model) {

        Long idCliente = Long.parseLong(cliID);
        Cliente cliente = clienteService.findById(idCliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("userID", cliente.getUserID());

        return "perfil/cliente/deletar-cliente";

    }

    @Transactional
    @GetMapping("/deletaCliente/{id}")
    public String excluirCliente(@PathVariable("id") String cliID) {

        Long idCliente = Long.parseLong(cliID);

        Cliente cliente = clienteService.findById(idCliente);

        clienteService.deleteById(idCliente);

        return "redirect:/login";


    }


}
