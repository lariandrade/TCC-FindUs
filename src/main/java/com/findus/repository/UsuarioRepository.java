package com.findus.repository;

import com.findus.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM tb_usuario WHERE user_Senha = :userSenha AND user_Email = :userEmail", nativeQuery = true)
    public Usuario Login(String userSenha, String userEmail);

    @Query(value = "SELECT * FROM tb_usuario WHERE user_Email = :userEmail", nativeQuery = true)
    public Usuario Remember(String userEmail);
}
