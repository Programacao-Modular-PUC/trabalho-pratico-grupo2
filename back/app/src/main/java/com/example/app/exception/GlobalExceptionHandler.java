package com.example.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataInvalidaException.class)
    public ResponseEntity<String> handleDataInvalida(DataInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuartoIndisponivelException.class)
    public ResponseEntity<String> handleQuartoIndisponivel(QuartoIndisponivelException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    
    // Adicione outros handlers conforme necessário
}
=======
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuartoIndisponivelException.class)
    public ResponseEntity<ErrorResponse> handleQuartoIndisponivel(QuartoIndisponivelException ex, WebRequest request) {
        return construirResposta(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(CapacidadeExcedidaException.class)
    public ResponseEntity<ErrorResponse> handleCapacidadeExcedida(CapacidadeExcedidaException ex, WebRequest request) {
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(DataInvalidaException.class)
    public ResponseEntity<ErrorResponse> handleDataInvalida(DataInvalidaException ex, WebRequest request) {
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(RecursoNaoPermitidoException.class)
    public ResponseEntity<ErrorResponse> handleRecursoNaoPermitido(RecursoNaoPermitidoException ex, WebRequest request) {
        return construirResposta(HttpStatus.FORBIDDEN, ex.getMessage(), request);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElement(NoSuchElementException ex, WebRequest request) {
        return construirResposta(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        return construirResposta(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return construirResposta(HttpStatus.BAD_REQUEST, mensagem, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        return construirResposta(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado: " + ex.getMessage(), request);
    }

    private ResponseEntity<ErrorResponse> construirResposta(HttpStatus status, String mensagem, WebRequest request) {
        String caminho = request.getDescription(false).replace("uri=", "");
        ErrorResponse erro = new ErrorResponse(status.value(), status.getReasonPhrase(), mensagem, caminho);
        return new ResponseEntity<>(erro, status);
    }
}
>>>>>>> 59ed827f9082e310da594185d46356a6fcbd4e65
