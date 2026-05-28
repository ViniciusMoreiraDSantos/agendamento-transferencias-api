package com.agendamento.transferencias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaxaNaoAplicavelException.class)
    public ResponseEntity<ErroResponseDto> handleTaxaNaoAplicavel(TaxaNaoAplicavelException ex) {
        return buildError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                campos.put(error.getField(), error.getDefaultMessage())
        );

        ErroResponseDto body = ErroResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .erro("Dados inválidos")
                .timestamp(LocalDateTime.now())
                .campos(campos)
                .build();

        return ResponseEntity.badRequest().body(body);
    }

    private ResponseEntity<ErroResponseDto> buildError(HttpStatus status, String mensagem) {
        ErroResponseDto body = ErroResponseDto.builder()
                .status(status.value())
                .erro(mensagem)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(body);
    }
}
