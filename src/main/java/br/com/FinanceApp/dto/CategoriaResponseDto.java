package br.com.FinanceApp.dto;

public record CategoriaResponseDto(
        Long id,
        String nome,
        UsuarioResponseDto usuario) {
}