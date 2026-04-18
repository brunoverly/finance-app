package br.com.FinanceApp.service;

import br.com.FinanceApp.dto.LancamentoRequestDto;
import br.com.FinanceApp.dto.LancamentoResponseDto;
import br.com.FinanceApp.entity.Categoria;
import br.com.FinanceApp.entity.Lancamento;
import br.com.FinanceApp.entity.Usuario;
import br.com.FinanceApp.exception.UnauthorizedAcessException;
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
import org.springframework.security.core.context.SecurityContextHolder;
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
        Usuario usuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        Categoria categoria = categoriaRepository.findById(dto.categoriaId()).
                orElseThrow(() -> new EntityNotFoundException("Categoria com id{" + dto.categoriaId() + "} não localizado"));

        if(categoria.getUsuario().getId() != usuario.getId()) {
            throw new UnauthorizedAcessException("Categoria informada na requisição não pertence ao usuário autenticado");
        }

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
        Usuario usuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Page <Lancamento> lancamentos = lancamentoRepository.findAllFilteredByUser(pageable, usuario.getId());
        return lancamentos.map(mapper::EntityToResponse);
    }

    public ResponseEntity<LancamentoResponseDto> findById(Long id) {
        Usuario usuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lancamento com id {" + id + "} não localizado"));
        if(lancamento.getUsuario().getId() != usuario.getId()) {
            throw new UnauthorizedAcessException("Usuário autenticado não possui permissão para acessar este recurso");
        }
        return ResponseEntity.ok(mapper.EntityToResponse(lancamento));
    }

    public ResponseEntity<LancamentoResponseDto> update(Long id, @Valid LancamentoRequestDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lançamento com id {" + id + "} não localizado"));

        if(lancamento.getUsuario().getId() != usuario.getId()) {
            throw new UnauthorizedAcessException("Usuário autenticado não possui permissão para acessar este recurso");
        }

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                        .orElseThrow(() -> new EntityNotFoundException("Categoria com id {" + id + "} não localizada"));

        lancamento.setDescricao(dto.descricao());
        lancamento.setValor(dto.valor());
        lancamento.setTipo(dto.tipo());
        lancamento.setUsuario(usuario);
        lancamento.setCategoria(categoria);

        lancamentoRepository.save(lancamento);

        return ResponseEntity.ok(mapper.EntityToResponse(lancamento));
    }

    public ResponseEntity delete(Long id) {

        Usuario usuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        Lancamento lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lançamento com id {" + id + "} não localizado"));

        if(lancamento.getUsuario().getId() != usuario.getId()) {
            throw new UnauthorizedAcessException("Usuário autenticado não possui permissão para acessar este recurso");
        }
        lancamentoRepository.delete(lancamento);

        return ResponseEntity.noContent().build();
    }
}
