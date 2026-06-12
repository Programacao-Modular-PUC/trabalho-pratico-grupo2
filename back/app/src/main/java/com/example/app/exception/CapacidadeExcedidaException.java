package com.example.app.exception;

public class CapacidadeExcedidaException extends RuntimeException {
    public CapacidadeExcedidaException(String mensagem) {
        super(mensagem);
    }
}