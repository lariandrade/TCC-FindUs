package com.findus.repository;

import com.findus.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByUserEmailAndUserSenha(String userEmail, String userSenha);
    Cliente findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.userCPF_CNPJ = :userCpf_Cnpj")
    boolean existsByUserCpf_Cnpj(@Param("userCpf_Cnpj") String userCpf_Cnpj);



}
