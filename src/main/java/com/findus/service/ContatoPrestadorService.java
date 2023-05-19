package com.findus.service;

import com.findus.models.ContatoPrestador;
import com.findus.repository.ContatoPrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoPrestadorService {

    @Autowired
    private ContatoPrestadorRepository ContatoPrestadorRepository;

    public ContatoPrestador save(ContatoPrestador contatoPrestador) {

        return ContatoPrestadorRepository.save(contatoPrestador);

    }

}
