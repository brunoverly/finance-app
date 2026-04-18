package br.com.FinanceApp.service;

import br.com.FinanceApp.dto.LoginDto;
import br.com.FinanceApp.dto.UsuarioRequestDto;
import br.com.FinanceApp.entity.Usuario;
import br.com.FinanceApp.mapper.EntityToDtoMapper;
import br.com.FinanceApp.repository.UsuarioRepository;
import br.com.FinanceApp.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityToDtoMapper mapper;

    @Autowired
    private TokenService tokenService;


    public ResponseEntity<String> create(UsuarioRequestDto dto) throws BadRequestException {
        if(!dto.senha().equals(dto.senha())) {
            throw new BadRequestException("Senhas informadas não conferem");
        }
        Usuario usuario = mapper.RequestToEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().body(tokenService.generateToken(usuario));
    }

    public ResponseEntity<String> login(LoginDto dto) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(dto.email());
        if(usuario == null) {
            throw new EntityNotFoundException("Email não localizado no banco de dados");
        }
        if(!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new BadRequestException("Senha incorreta!");
        }
        return ResponseEntity.ok().body(tokenService.generateToken(usuario));


    }
}
