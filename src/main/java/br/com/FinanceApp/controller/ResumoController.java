package br.com.FinanceApp.controller;


import br.com.FinanceApp.dto.ResumoMensalDto;
import br.com.FinanceApp.exception.ErrorResponse;
import br.com.FinanceApp.service.ResumoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {
    @Autowired
    private ResumoService resumoService;

    @Operation(
            summary = "Buscar resumo mensal",
            description = "Busca o resumo mensal de despesas e gastos do usuário"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = "aplication/json",
                            schema = @Schema(implementation = ResumoMensalDto.class)
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
    public ResponseEntity<ResumoMensalDto> getResumo() {
        return resumoService.getResumo();
    }
}
