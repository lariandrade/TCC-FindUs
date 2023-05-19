package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contato_prestador")
public class ContatoPrestador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contID", nullable = false)
    private Long contID;

    @Column(name = "contTelefone", length = 13, nullable = false)
    private String contTelefone;

    @Column(name = "contEmail", length = 50, nullable = false)
    private String contEmail;

    @Column(name = "contIdCliente", nullable = false)
    private Long contIdCliente;

    @Column(name = "contIdPrestador", nullable = false)
    private Long contIdPrestador;

}
