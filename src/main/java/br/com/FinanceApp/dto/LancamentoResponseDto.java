package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LancamentoResponseDto(
        @Schema(description = "ID do lançamento", example = "1")
        Long id,
        @Schema(description = "Descrição do lançamento", example = "Uber")
        String descricao,
        @Schema(description = "Valor do laçamento", example = "21.22")
        BigDecimal valor,
        @Schema(description = "Data do lançamento", example = "21-04-2026 15:22:26")
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dataLancamento,
        @Schema(description = "RECEITA ou DESPESA", example = "DESPESA")
        TipoLancamento tipo,
        CategoriaResumoDto categoria,
        UsuarioResponseDto usuario) {
}
