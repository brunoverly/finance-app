package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.AuthResponseDto;
import br.com.FinanceApp.dto.LoginDto;
import br.com.FinanceApp.dto.UsuarioRequestDto;
import br.com.FinanceApp.exception.ErrorResponse;
import br.com.FinanceApp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Operation(
            summary = "Cadastrar usuário",
            description = "Cria um novo usuário com login no banco"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Criado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = AuthResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email já cadastrado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @PostMapping("/registro")
    public ResponseEntity<AuthResponseDto> create(@Valid @RequestBody UsuarioRequestDto dto) throws BadRequestException {
        return authService.create(dto);
    }


    @Operation(
            summary = "Efetuar login",
            description = "Realiza login e autentica o usuário"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = AuthResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginDto dto) throws Exception {
        return authService.login(dto);
    }
}
