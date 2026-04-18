package br.com.FinanceApp.service;


import br.com.FinanceApp.dto.CategoriaRequestDto;
import br.com.FinanceApp.dto.CategoriaResponseDto;
import br.com.FinanceApp.entity.Categoria;
import br.com.FinanceApp.entity.Usuario;
import br.com.FinanceApp.mapper.EntityToDtoMapper;
import br.com.FinanceApp.repository.CategoriaRepository;
import br.com.FinanceApp.repository.UsuarioRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EntityToDtoMapper mapper;

    public ResponseEntity<CategoriaResponseDto> create(@Valid CategoriaRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com id {" + dto.usuarioId() + " não localizado"));

        Categoria categoria = Categoria.builder()
                .nome(dto.nome())
                .usuario(usuario)
                .build();

        categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(uri).body(mapper.EntityToResponse(categoria));
    }

    public Page<CategoriaResponseDto> findAll(Pageable pageable) {
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        return categorias.map(mapper::EntityToResponse);
    }
}
