package com.findus.repository;

import com.findus.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByUserEmailAndUserSenha(String userEmail, String userSenha);
    Cliente findByUserEmail(String userEmail);
}
