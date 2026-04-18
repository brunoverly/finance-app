package br.com.FinanceApp.dto;

import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDto(
        @NotNull(message = "Campo obrigatório")
        String nome,
        @NotNull(message = "Campo obrigatório")
        Long usuarioId) {
}