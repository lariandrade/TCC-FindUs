package com.findus.exception;

public class PrestadorNotFoundException extends RuntimeException {

    public PrestadorNotFoundException(Long id) {
        super("Não foi possível encontrar o prestador com o ID " + id);
    }
}
