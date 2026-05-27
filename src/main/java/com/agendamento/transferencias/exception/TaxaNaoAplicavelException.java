package com.agendamento.transferencias.exception;

public class TaxaNaoAplicavelException extends RuntimeException {
    public TaxaNaoAplicavelException(String message) {
        super(message);
    }
}
