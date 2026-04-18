package br.com.FinanceApp.service;

import br.com.FinanceApp.dto.AuthResponseDto;
import br.com.FinanceApp.dto.LoginDto;
import br.com.FinanceApp.dto.UsuarioRequestDto;
import br.com.FinanceApp.entity.Usuario;
import br.com.FinanceApp.exception.EmailNotUniqueException;
import br.com.FinanceApp.mapper.EntityToDtoMapper;
import br.com.FinanceApp.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityToDtoMapper mapper;

    @Autowired
    private JwtService tokenService;


    public ResponseEntity<AuthResponseDto> create(UsuarioRequestDto dto) throws BadRequestException {
        if(!dto.senha().equals(dto.senha())) {
            throw new BadRequestException("Senhas informadas não conferem");
        }
        if(usuarioRepository.existsByEmail(dto.email())) {
            throw new EmailNotUniqueException("Email {"+ dto.email() +"} já em uso");
        }

        Usuario usuario = mapper.RequestToEntity(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        usuarioRepository.save(usuario);

        AuthResponseDto auth = new  AuthResponseDto(usuario.getNome(), tokenService.generateToken(usuario));
        return ResponseEntity.ok().body(auth);
    }

    public ResponseEntity<AuthResponseDto> login(LoginDto dto) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(dto.email());
        if(usuario == null) {
            throw new EntityNotFoundException("Email não localizado no banco de dados");
        }
        if(!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new BadRequestException("Senha incorreta!");
        }

        AuthResponseDto auth = new  AuthResponseDto(usuario.getNome(), tokenService.generateToken(usuario));
        return ResponseEntity.ok().body(auth);


    }
}
