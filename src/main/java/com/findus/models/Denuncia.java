package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "denuncia")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "denunID", nullable = false)
    private Long denunID;

    @Column(name = "denunData", columnDefinition = "DATE", nullable = false)
    private Date denunData;

    @Column(name = "denunIdUsuario", length = 10, nullable = false)
    private Integer denunIdUsuario;

    @Column(name = "denunIdDenunciado", length = 10, nullable = false)
    private Integer denunIdDenunciado;

    @Column(name = "denunMotivo", columnDefinition = "TEXT", nullable = false)
    private String denunMotivo;


}
