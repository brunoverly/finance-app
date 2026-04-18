package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDto(
        @NotBlank(message = "Campo obrigatório")
        String nome,
        @Email(message = "Campo obrigatório")
        String email,
        @NotBlank(message = "Campo obrigatório")
        String senha,
        @NotBlank(message = "Campo obrigatório")
        String confirmarSenha,
        UsuarioRole role
) {
}
