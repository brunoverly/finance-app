package br.com.FinanceApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CategoriaResponseDto(
        @Schema(description = "ID da categoria", example = "1")
        Long id,
        @Schema(description = "Nome da categoria", example = "Transporte")
        String nome,
        UsuarioResponseDto usuario) {
}