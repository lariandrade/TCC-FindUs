package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="portifolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prestID", nullable = false)
    private Long prestID;

    @Column(name = "portTituloPrj", length = 255, nullable = false)
    private String portTituloPrj;

    @Column(name = "portSegmento", length = 100, nullable = false)
    private String portSegmento;

    @Column(name = "portTempo", nullable = false)
    private int portTempo;

    @Column(name = "portOrcamento", length = 100, nullable = false)
    private String portOrcamento;

    @Lob
    @Column(name = "portImagem", nullable = false, insertable=false)
    private byte[] portImagem;

    @Column(name = "portClassificacao", length = 50, nullable = false)
    private int portClassificacao;

    @Column(name = "portDescricao", length = 255, nullable = false)
    private String portDescricao;


    @ManyToOne
    @JoinColumn(name = "prestador_ID")
    private Prestador prestador;

}
