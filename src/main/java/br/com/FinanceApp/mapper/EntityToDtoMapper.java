package br.com.FinanceApp.mapper;

import br.com.FinanceApp.dto.UsuarioRequestDto;
import br.com.FinanceApp.dto.UsuarioResponseDto;
import br.com.FinanceApp.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityToDtoMapper {
    Usuario RequestToEntity(UsuarioRequestDto usuarioRequestDto);
    UsuarioResponseDto EntityToResponse(Usuario usuario);
    List<UsuarioResponseDto> EntityToResponseList(List<Usuario> usuarios);
}
