package br.com.FinanceApp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record ErrorResponse(
        @Schema(description = "Carimbo", example = "21-04-2026 15:22:26")
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime timestamp,
        @Schema(description = "Código do erro", example = "403")
        int status,
        @Schema(description = "Tipo de erro", example = "FORBIDDEN")
        String error,
        @Schema(description = "Mensagem do erro", example = "Usuário não possui acesso a este recurso")
        String message,
        @Schema(description = "Caminho da requisição", example = "/lancamentos/1")
        String path
) {
}
