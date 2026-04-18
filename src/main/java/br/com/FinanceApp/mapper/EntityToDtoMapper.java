package br.com.FinanceApp.mapper;

import br.com.FinanceApp.dto.*;
import br.com.FinanceApp.entity.Categoria;
import br.com.FinanceApp.entity.Lancamento;
import br.com.FinanceApp.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityToDtoMapper {
    Usuario RequestToEntity(UsuarioRequestDto usuarioRequestDto);
    UsuarioResponseDto EntityToResponse(Usuario usuario);
    List<UsuarioResponseDto> EntityToResponseList(List<Usuario> usuarios);

    LancamentoResponseDto EntityToResponse(Lancamento lancamento);
    Lancamento RequestToEntity(LancamentoRequestDto lancamentoRequestDto);

    Categoria RequestToEntity(CategoriaRequestDto categoriaRequestDto);
    CategoriaResponseDto EntityToResponse(Categoria categoria);
}
