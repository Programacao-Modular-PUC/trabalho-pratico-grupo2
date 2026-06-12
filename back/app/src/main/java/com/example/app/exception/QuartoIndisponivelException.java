package com.example.app.exception;

public class QuartoIndisponivelException extends RuntimeException {
    public QuartoIndisponivelException(String mensagem) {
        super(mensagem);
    }
}