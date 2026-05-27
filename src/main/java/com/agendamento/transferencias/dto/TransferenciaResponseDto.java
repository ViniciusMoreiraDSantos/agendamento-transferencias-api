package com.agendamento.transferencias.dto;

import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransferenciaResponseDto {
    private Long id;
    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;
}
