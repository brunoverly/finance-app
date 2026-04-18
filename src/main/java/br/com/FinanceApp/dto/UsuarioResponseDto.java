package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioResponseDto(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {
}
