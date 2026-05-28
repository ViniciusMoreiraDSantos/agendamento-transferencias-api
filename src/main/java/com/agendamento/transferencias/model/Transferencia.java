package com.agendamento.transferencias.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transferencias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conta_origem", nullable = false, length = 10)
    private String contaOrigem;

    @Column(name = "conta_destino", nullable = false, length = 10)
    private String contaDestino;

    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "taxa", nullable = false, precision = 15, scale = 2)
    private BigDecimal taxa;

    @Column(name = "data_transferencia", nullable = false)
    private LocalDate dataTransferencia;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDate dataAgendamento;

}
