package com.agendamento.transferencias.service;

import com.agendamento.transferencias.exception.TaxaNaoAplicavelException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaxaServiceTest {

    private final TaxaService taxaService = new TaxaService();
    private final LocalDate hoje = LocalDate.now();

    @Test
    void deveCobrarTaxaFixaMaisPercentualParaDiaZero() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje);
        assertEquals(new BigDecimal("28.00"), taxa);
    }

    @Test
    void deveCobrarTaxaFixaParaDia1() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(1));
        assertEquals(new BigDecimal("12.00"), taxa);
    }

    @Test
    void deveCobrarTaxaFixaParaDia10() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(10));
        assertEquals(new BigDecimal("12.00"), taxa);
    }

    @Test
    void deveLancarExcecaoParaDia11() {
        assertThrows(TaxaNaoAplicavelException.class,
                () -> taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(11)));
    }

    @Test
    void deveLancarExcecaoParaDia20() {
        assertThrows(TaxaNaoAplicavelException.class,
                () -> taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(20)));
    }

    @Test
    void deveCobrar8p2PercentParaDia21() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(21));
        assertEquals(new BigDecimal("82.00"), taxa);
    }

    @Test
    void deveCobrar8p2PercentParaDia30() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(30));
        assertEquals(new BigDecimal("82.00"), taxa);
    }

    @Test
    void deveCobrar6p9PercentParaDia31() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(31));
        assertEquals(new BigDecimal("69.00"), taxa);
    }

    @Test
    void deveCobrar6p9PercentParaDia40() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(40));
        assertEquals(new BigDecimal("69.00"), taxa);
    }

    @Test
    void deveCobrar4p7PercentParaDia41() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(41));
        assertEquals(new BigDecimal("47.00"), taxa);
    }

    @Test
    void deveCobrar4p7PercentParaDia50() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(50));
        assertEquals(new BigDecimal("47.00"), taxa);
    }

    @Test
    void deveCobrar1p7PercentParaDia51() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(51));
        assertEquals(new BigDecimal("17.00"), taxa);
    }

    @Test
    void deveCobrar1p7PercentParaAcimaDe50Dias() {
        BigDecimal taxa = taxaService.calcular(new BigDecimal("1000.00"), hoje, hoje.plusDays(100));
        assertEquals(new BigDecimal("17.00"), taxa);
    }
}
