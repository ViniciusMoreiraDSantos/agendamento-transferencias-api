package com.agendamento.transferencias.exception;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErroResponseDto {
    private int status;
    private String erro;
    private LocalDateTime timestamp;
    private Map<String, String> campos;
}
