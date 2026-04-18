package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.UsuarioRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioResponseDto(
        @Schema(description = "ID do usuário", example = "1")
        Long id,
        @Schema(description = "Nome do usuário", example = "Bruno Martins")
        String nome,
        @Schema(description = "Email do usuário", example = "bruno@email.com")
        String email,
        @Schema(description = "ADMIN ou USER", example = "USER")
        UsuarioRole role
) {
}
