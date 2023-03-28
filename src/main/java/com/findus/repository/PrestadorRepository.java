package com.findus.repository;

import com.findus.models.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorRepository extends JpaRepository<Prestador,Long> {
    Prestador findByUserEmailAndUserSenha(String userEmail, String userSenha);
    Prestador findByUserEmail(String userEmail);
}
