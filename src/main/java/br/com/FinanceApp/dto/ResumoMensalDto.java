package br.com.FinanceApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ResumoMensalDto(
        @Schema(description = "Valor total de receitas", example = "185.55")
        BigDecimal totalReceitas,
        @Schema(description = "Valor total de despesas", example = "98.77")
        BigDecimal totalDespesas,
        @Schema(description = "Saldo final", example = "86.78")
        BigDecimal saldo) {
}
