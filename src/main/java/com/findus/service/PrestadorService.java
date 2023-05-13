package com.findus.service;

import com.findus.exception.UsuarioException;
import com.findus.exception.PrestadorNotFoundException;
import com.findus.models.Prestador;
import com.findus.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PrestadorService {

    @Autowired
    private PrestadorRepository prestadorRepository;

    public Prestador findById(Long id) {
        Optional<Prestador> prestador = prestadorRepository.findById(id);
        return prestador.orElseThrow(() -> new PrestadorNotFoundException(id));
    }

    public List<Prestador> findAll() {
        return prestadorRepository.findAll();
    }

    public Prestador save(Prestador prestador) {

        String userEmail = prestador.getUserEmail();
        String userCPF_CNPJ = prestador.getUserCPF_CNPJ();

        // Verifica se já existe um cliente com o mesmo e-mail ou CPF
        if (prestadorRepository.existsByUserEmail(userEmail) || prestadorRepository.existsByUserCpf_Cnpj(userCPF_CNPJ)) {
            throw new UsuarioException("Já existe um usuario cadastrado com o mesmo e-mail ou CPF/CNPJ");
        }

        return prestadorRepository.save(prestador);
    }

    public Prestador update(Prestador prestador) {

        return prestadorRepository.save(prestador);
    }

    public void deleteById(Long id) {

        prestadorRepository.deleteById(id);
    }
}
