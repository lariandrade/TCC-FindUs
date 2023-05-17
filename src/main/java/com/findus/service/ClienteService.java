package com.findus.service;

import com.findus.exception.PrestadorNotFoundException;
import com.findus.exception.UsuarioException;
import com.findus.models.Cliente;
import com.findus.models.Prestador;
import com.findus.repository.ClienteRepository;
import com.findus.repository.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    private final PrestadorRepository prestadorRepository;

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new PrestadorNotFoundException(id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {

        String userEmail = cliente.getUserEmail();
        String userCPF_CNPJ = cliente.getUserCPF_CNPJ();

        // Verifica se já existe um cliente com o mesmo e-mail ou CPF
        if (clienteRepository.existsByUserEmail(userEmail) || clienteRepository.existsByUserCpf_Cnpj(userCPF_CNPJ)) {
            throw new UsuarioException("Já existe um usuario cadastrado com o mesmo e-mail ou CPF/CNPJ");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {

        clienteRepository.deleteById(id);
    }

    @Autowired
    public ClienteService(PrestadorRepository prestadorRepository) {
        this.prestadorRepository = prestadorRepository;
    }

    public List<Prestador> filtrarPrestadoresPorObjetivo(List<String> objetivo) {
        return prestadorRepository.findByUserSegmentoIn(objetivo);
    }
}
