package br.com.FinanceApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @Email(message = "Campo obrigatório")
        String email,
        @NotBlank(message = "Campo obrigatório")
        String senha
) {
}
