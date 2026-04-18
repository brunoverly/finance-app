package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.LoginDto;
import br.com.FinanceApp.dto.UsuarioRequestDto;
import br.com.FinanceApp.dto.UsuarioResponseDto;
import br.com.FinanceApp.service.UsuarioService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<String> create(@Valid @RequestBody UsuarioRequestDto dto) throws BadRequestException {
        return usuarioService.create(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto dto) throws Exception {
        return usuarioService.login(dto);
    }
}
