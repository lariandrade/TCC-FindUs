package com.findus.exception;

public class PortfolioNotFoundException extends RuntimeException {

    public PortfolioNotFoundException(Long id) {
        super("Não foi possível encontrar o portfolio com " + id);
    }
}
