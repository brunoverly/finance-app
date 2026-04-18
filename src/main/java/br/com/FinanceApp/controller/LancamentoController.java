package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.CategoriaResponseDto;
import br.com.FinanceApp.dto.LancamentoRequestDto;
import br.com.FinanceApp.dto.LancamentoResponseDto;
import br.com.FinanceApp.exception.ErrorResponse;
import br.com.FinanceApp.service.LancamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lancamentos")
@SecurityRequirement(name = "bearerAuth")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;

    @Operation(
            summary = "Cadastrar lançamento",
            description = "Cria um novo lançamento no banco"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Criado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = LancamentoResponseDto.class)
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
                    responseCode = "401",
                    description = "Token ausente ou inválido",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            )
    })
    @PostMapping
    public ResponseEntity<LancamentoResponseDto> create (@Valid @RequestBody LancamentoRequestDto dto) {
        return lancamentoService.create(dto);
    }

    @Operation(
            summary = "Listar lançamentos",
            description = "Lista todas os lançamentos que pertencem ao usuário"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = LancamentoResponseDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            )
    })
    @GetMapping
    public Page<LancamentoResponseDto> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return lancamentoService.findAll(pageable);
    }


    @Operation(
            summary = "Buscar um lançamento",
            description = "Busca um lançamento do usuário pelo ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = LancamentoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não localizado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<LancamentoResponseDto> findById(@PathVariable Long id) {
        return lancamentoService.findById(id);
    }

    @Operation(
            summary = "Atualizar um lançamento",
            description = "Atualiza um lançamento do usuário pelo ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = LancamentoResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não localizado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<LancamentoResponseDto> update(@PathVariable Long id, @Valid @RequestBody LancamentoRequestDto dto) {
        return lancamentoService.update(id, dto);
    }


    @Operation(
            summary = "Apaga um lançamento",
            description = "Apaga um lançamento do usuário pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Não localizado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )

            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return lancamentoService.delete(id);
    }
}
