package com.findus.repository;

import com.findus.models.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrestadorRepository extends JpaRepository<Prestador, Long> {
    Prestador findByUserEmailAndUserSenha(String userEmail, String userSenha);

    Prestador findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Prestador c WHERE c.userCPF_CNPJ = :userCpf_Cnpj")
    boolean existsByUserCpf_Cnpj(@Param("userCpf_Cnpj") String userCpf_Cnpj);

}
