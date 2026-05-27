package com.agendamento.transferencias.service;

import com.agendamento.transferencias.exception.TaxaNaoAplicavelException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class TaxaService {
    private static final BigDecimal TAXA_DIA_0_FIXA    = new BigDecimal("3.00");
    private static final BigDecimal TAXA_DIA_0_PERCENT = new BigDecimal("0.025");
    private static final BigDecimal TAXA_1_10_FIXA     = new BigDecimal("12.00");
    private static final BigDecimal TAXA_21_30_PERCENT = new BigDecimal("0.082");
    private static final BigDecimal TAXA_31_40_PERCENT = new BigDecimal("0.069");
    private static final BigDecimal TAXA_41_50_PERCENT = new BigDecimal("0.047");
    private static final BigDecimal TAXA_ACIMA_50_PERCENT = new BigDecimal("0.017");

    public BigDecimal  calcular(BigDecimal valor, LocalDate dataAgendamento, LocalDate dataTransferencia) {
        long dias = ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);

        if(dias == 0)return TAXA_DIA_0_FIXA.add(percentual(valor, TAXA_DIA_0_PERCENT));
        if(dias <= 10) return TAXA_1_10_FIXA;
        if(dias <= 20) throw new
                TaxaNaoAplicavelException("Não há taxa aplicável para transferências entre 11 e 20 dias.");
        if(dias <= 30) return percentual(valor, TAXA_21_30_PERCENT);
        if(dias <= 40) return percentual(valor, TAXA_31_40_PERCENT);
        if(dias <= 50) return percentual(valor, TAXA_41_50_PERCENT);
        return percentual(valor, TAXA_ACIMA_50_PERCENT);

    }

    private BigDecimal percentual(BigDecimal valor, BigDecimal taxa){
        return valor.multiply(taxa).setScale(2, RoundingMode.HALF_UP);
    }
}
