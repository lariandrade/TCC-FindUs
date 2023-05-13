package com.findus.service;

import com.findus.exception.ClienteExistenteException;
import com.findus.exception.PrestadorNotFoundException;
import com.findus.models.Cliente;
import com.findus.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

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
            throw new ClienteExistenteException("Já existe um usuario cadastrado com o mesmo e-mail ou CPF/CNPJ");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {

        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {

        clienteRepository.deleteById(id);
    }
}
