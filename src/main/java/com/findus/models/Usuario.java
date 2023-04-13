package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "userTipo", discriminatorType = DiscriminatorType.STRING)
@MappedSuperclass
public abstract class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Long id_Usuario;

    @Column(name = "userNome", length = 50, nullable = false)
    private String userNome;

    @Column(name = "userCPF_CNPJ", length = 14, nullable = false)
    private String userCPF_CNPJ;

    @Column(name = "userTelefone", length = 13, nullable = false)
    private String userTelefone;

    @Column(name = "userEmail", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "userSenha", length = 8, nullable = false)
    private String userSenha;

    @Column(name = "userSegmento", length = 50, nullable = false)
    private String userSegmento;

    @Column(name = "userTipo", length = 50, nullable = false)
    private String userTipo;


    @Column(name = "userDescricao", length = 250, nullable = false)
    private String userDescricao;

    @Lob
    @Column(name = "userFoto", nullable = false)
    private byte[] userFoto;

    public Usuario() {

    }

}
