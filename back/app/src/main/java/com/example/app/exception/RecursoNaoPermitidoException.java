package com.example.app.exception;

public class RecursoNaoPermitidoException extends RuntimeException {
    public RecursoNaoPermitidoException(String mensagem) {
        super(mensagem);
    }
}