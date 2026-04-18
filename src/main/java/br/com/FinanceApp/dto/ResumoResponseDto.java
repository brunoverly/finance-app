package br.com.FinanceApp.dto;

import java.math.BigDecimal;

public record ResumoResponseDto(
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal saldo) {
}
