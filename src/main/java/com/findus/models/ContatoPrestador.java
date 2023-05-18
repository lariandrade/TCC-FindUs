package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "contato_prestador")
public class ContatoPrestador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contID", nullable = false)
    private Long avaID;


    @Column(name = "contData", columnDefinition = "DATE", nullable = false)
    private Date contData;

    @Column(name = "contMensagem", columnDefinition = "TEXT", nullable = false)
    private String contMensagem;

    @Column(name = "contTelefone", length = 13, nullable = false)
    private String contTelefone;

    @Column(name = "contEmail", length = 50, nullable = false)
    private String contEmail;

    @Column(name = "contIdCliente", length = 10, nullable = false)
    private Integer contIdCliente;

    @Column(name = "contIdPrestador", length = 10, nullable = false)
    private Integer contIdPrestador;

}
