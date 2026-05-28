package com.agendamento.transferencias.controller;

import com.agendamento.transferencias.dto.TransferenciaRequestDto;
import com.agendamento.transferencias.dto.TransferenciaResponseDto;

import com.agendamento.transferencias.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TransferenciaController {
    private final TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<TransferenciaResponseDto> agendar(@RequestBody @Valid TransferenciaRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaService.agendar(requestDto));
    }


    @GetMapping
    public ResponseEntity<List<TransferenciaResponseDto>> listarTodos(){
        return ResponseEntity.ok(transferenciaService.listarTodos());
    }
}
