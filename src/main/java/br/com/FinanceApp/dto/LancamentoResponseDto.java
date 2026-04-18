package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.TipoLancamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LancamentoResponseDto(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDateTime dataLancamento,
        TipoLancamento tipo,
        Long  categoriaId,
        Long usuarioId) {
}
