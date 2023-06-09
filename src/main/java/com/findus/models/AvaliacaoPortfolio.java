package com.findus.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "avaliacao_portfolio")
public class AvaliacaoPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avaID", nullable = false)
    private Long avaID;

    @Column(name = "avaNota", length = 10, nullable = false)
    private Integer avaNota;

    @Column(name = "avaIdProjeto", length = 10, nullable = false)
    private Long avaIdProjeto;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;


}
