package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LancamentoResponseDto(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDateTime dataLancamento,
        TipoLancamento tipo,
        CategoriaResumoDto categoria,
        UsuarioResponseDto usuario) {
}
