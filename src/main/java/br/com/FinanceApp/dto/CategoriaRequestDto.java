package br.com.FinanceApp.dto;

import br.com.FinanceApp.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDto(
        @NotNull(message = "Campo obrigatório")
        String nome,
        @NotNull(message = "Campo obrigatório")
        Long usuarioId) {
}