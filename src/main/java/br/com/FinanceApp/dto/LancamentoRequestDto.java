package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LancamentoRequestDto(
        @NotBlank(message = "Campo obrigatório")
        String descricao,
        @Positive(message = "Valor deve ser maior que 0")
        BigDecimal valor,
        TipoLancamento tipo,
        @NotNull(message = "Campo obrigatório")
        Long  categoriaId,
        @NotNull(message = "Campo obrigatório")
        Long usuarioId) {
}
