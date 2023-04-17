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

    @Column(name = "portConteudo", length = 255, nullable = false, insertable=false)
    private String portConteudo;

    @Lob
    @Column(name = "portImagem", nullable = false, insertable=false)
    private byte[] portImagem;

    @Column(name = "portClassificacao", length = 50)
    private int portClassificacao;

    @Column(name = "portSegmento", length = 255, nullable = false)
    private String portSegmento;

    @Column(name = "portTituloPrj", length = 255, nullable = false)
    private String portTituloPrj;

    @ManyToOne
    @JoinColumn(name = "prestador_ID")
    private Prestador prestador;

}
