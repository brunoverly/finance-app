package br.com.FinanceApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @Schema(description = "Email do usuário", example = "bruno@email.com")
        @Email(message = "Campo obrigatório")
        String email,
        @Schema(description = "Senha do usuário", example = "158463@4596%sanoel")
        @NotBlank(message = "Campo obrigatório")
        String senha
) {
}
