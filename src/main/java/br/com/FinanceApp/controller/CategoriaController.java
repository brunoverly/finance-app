package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.AuthResponseDto;
import br.com.FinanceApp.dto.CategoriaRequestDto;
import br.com.FinanceApp.dto.CategoriaResponseDto;
import br.com.FinanceApp.dto.LancamentoResponseDto;
import br.com.FinanceApp.exception.ErrorResponse;
import br.com.FinanceApp.service.CategoriaService;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
@SecurityRequirement(name = "bearerAuth")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Operation(
            summary = "Cadastrar categoria",
            description = "Cria uma nova categoria no banco"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Criado",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = CategoriaResponseDto.class)
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

            )
    })
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(@Valid @RequestBody CategoriaRequestDto dto) {
        return categoriaService.create(dto);
    }

    @Operation(
            summary = "Listar categorias",
            description = "Lista todas as categorias que pertencem ao usuário"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "aplication/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoriaResponseDto.class))
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
    public Page<CategoriaResponseDto> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return categoriaService.findAll(pageable);
    }

}
