package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.TipoLancamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LancamentoRequestDto(
        @Schema(description = "Descrição do lançamento", example = "Uber")
        @NotBlank(message = "Campo obrigatório")
        String descricao,
        @Schema(description = "Valor gasto", example = "21.22")
        @Positive(message = "Valor deve ser maior que 0")
        BigDecimal valor,
        @Schema(description = "RECEITA ou DESPESA", example = "DESPESA")
        TipoLancamento tipo,
        @Schema(description = "ID da categoria", example = "1")
        @NotNull(message = "Campo obrigatório")
        Long  categoriaId) {
}
