package com.findus.DTO;

import com.findus.models.Prestador;

public class AvaliacaoPrestador {
    private Prestador prestador;
    private int somaAvaliacoes;

    public AvaliacaoPrestador(Prestador prestador, int somaAvaliacoes) {
        this.prestador = prestador;
        this.somaAvaliacoes = somaAvaliacoes;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public int getSomaAvaliacoes() {
        return somaAvaliacoes;
    }

    public void setSomaAvaliacoes(int somaAvaliacoes) {
        this.somaAvaliacoes = somaAvaliacoes;
    }
}
