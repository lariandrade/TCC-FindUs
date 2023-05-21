package com.findus.service;

import com.findus.exception.UsuarioException;
import com.findus.models.ContatoPrestador;
import com.findus.repository.ContatoPrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoPrestadorService {

    @Autowired
    private ContatoPrestadorRepository ContatoPrestadorRepository;

    public ContatoPrestador save(ContatoPrestador contatoPrestador) {

        return ContatoPrestadorRepository.save(contatoPrestador);

    }

    public ContatoPrestador findById(Long id) {
        Optional<ContatoPrestador> ContatoPrestador = ContatoPrestadorRepository.findById(id);
        return ContatoPrestador.orElseThrow(() -> new UsuarioException("Não encontrato nenhum contato."));

    }

}
