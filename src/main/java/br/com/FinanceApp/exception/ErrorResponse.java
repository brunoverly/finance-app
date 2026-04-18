package br.com.FinanceApp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record ErrorResponse(
        @Schema(description = "Carimbo", example = "2026-04-21T15:22:26")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp,
        @Schema(description = "Código do erro", example = "400")
        int status,
        @Schema(description = "Tipo de erro", example = "BAD_REQUEST")
        String error,
        @Schema(description = "Mensagem do erro", example = "Ocorreu um erro na requisição")
        String message,
        @Schema(description = "Caminho da requisição", example = "/lancamentos/1")
        String path
) {
}
