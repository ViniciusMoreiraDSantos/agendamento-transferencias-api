package com.agendamento.transferencias.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class TransferenciaRequestDto {
    @NotBlank(message = "Conta origem é obrigatória")
    @Pattern(regexp = "\\d{10}" , message = "A conta deve 10 digitos numericos")
    private String contaOrigem;

    @NotBlank(message = "Conta Destino é obrigatória")
    @Pattern(regexp = "\\d{10}" , message = "A conta deve ter 10 digitos numericos")
    private String contaDestino;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "Data de Transferencia é obrigatória")
    @FutureOrPresent(message = "A data deve ser posterior ou igual a hoje")
    private LocalDate dataTransferencia;

}
