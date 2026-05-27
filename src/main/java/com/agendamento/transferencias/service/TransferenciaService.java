package com.agendamento.transferencias.service;

import com.agendamento.transferencias.dto.TransferenciaRequestDto;
import com.agendamento.transferencias.dto.TransferenciaResponseDto;
import com.agendamento.transferencias.model.Transferencia;
import com.agendamento.transferencias.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

    private final TransferenciaRepository repository;
    private final TaxaService taxaService;

    @Transactional
    public TransferenciaResponseDto agendar(TransferenciaRequestDto dto) {
        LocalDate diaAtual = LocalDate.now();
        var taxa = taxaService.calcular(dto.getValor(), diaAtual, dto.getDataTransferencia());

        Transferencia t = Transferencia.builder().contaOrigem(dto.getContaOrigem()).
                contaDestino(dto.getContaDestino())
                .valor(dto.getValor())
                .taxa(taxa)
                .dataTransferencia(dto.getDataTransferencia())
                .dataAgendamento(diaAtual).build();

        return mapperManual(repository.save(t));
    }



    private TransferenciaResponseDto mapperManual(Transferencia t) {
        return TransferenciaResponseDto.builder()
                .id(t.getId())
                .contaOrigem(t.getContaOrigem())
                .contaDestino(t.getContaDestino())
                .valor(t.getValor())
                .taxa(t.getTaxa())
                .dataTransferencia(t.getDataTransferencia())
                .dataAgendamento(t.getDataAgendamento())
                .build();
    }
}
