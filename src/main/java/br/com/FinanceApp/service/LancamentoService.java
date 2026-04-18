package br.com.FinanceApp.service;

import br.com.FinanceApp.dto.LancamentoRequestDto;
import br.com.FinanceApp.dto.LancamentoResponseDto;
import br.com.FinanceApp.entity.Categoria;
import br.com.FinanceApp.entity.Lancamento;
import br.com.FinanceApp.entity.Usuario;
import br.com.FinanceApp.mapper.EntityToDtoMapper;
import br.com.FinanceApp.repository.CategoriaRepository;
import br.com.FinanceApp.repository.LancamentoRepository;
import br.com.FinanceApp.repository.UsuarioRepository;
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
public class LancamentoService {
    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EntityToDtoMapper mapper;

    public ResponseEntity<LancamentoResponseDto> create(@Valid LancamentoRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId()).
                orElseThrow(() -> new EntityNotFoundException("Usuário com id{" + dto.usuarioId() + "} não localizado"));

        Categoria categoria = categoriaRepository.findById(dto.usuarioId()).
                orElseThrow(() -> new EntityNotFoundException("Categoria com id{" + dto.categoriaId() + "} não localizado"));

        Lancamento lancamento = Lancamento.builder()
                .descricao(dto.descricao())
                .valor(dto.valor())
                .tipo(dto.tipo())
                .usuario(usuario)
                .categoria(categoria)
                .build();

        lancamentoRepository.save(lancamento);

        URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lancamento.getId())
                .toUri();

        return ResponseEntity.created(uri).body(mapper.EntityToResponse(lancamento));
    }

    public Page<LancamentoResponseDto> findAll(Pageable pageable) {
        Page <Lancamento> lancamentos = lancamentoRepository.findAll(pageable);
        return lancamentos.map(mapper::EntityToResponse);
    }

    public ResponseEntity<LancamentoResponseDto> findById(Long id) {
        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lancamento com id {" + id + "} não localizado"));

        return ResponseEntity.ok(mapper.EntityToResponse(lancamento));
    }
}
