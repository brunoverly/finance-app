package br.com.FinanceApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDto(
        @Schema(description = "Nome da categoria", example = "Transporte")
        @NotNull(message = "Campo obrigatório")
        String nome) {
}