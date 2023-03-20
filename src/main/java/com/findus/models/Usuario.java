package com.findus.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Tb_Usuario")
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Long id_Usuario;

    @Column(name = "userNome", length = 50, nullable = false)
    private String userNome;

    @Column(name = "userCPF_CNPJ", length = 14, nullable = false)
    private Integer userCPF_CNPJ;

    @Column(name = "userTelefone", length = 9, nullable = false)
    private Integer userTelefone;

    @Column(name = "userEmail", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "userSenha", length = 8, nullable = false)
    private String userSenha;

    @Column(name = "segmento", length = 50, nullable = false)
    private String segmento;

    public Usuario() {

    }

}
