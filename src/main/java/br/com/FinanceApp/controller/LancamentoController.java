package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.LancamentoRequestDto;
import br.com.FinanceApp.dto.LancamentoResponseDto;
import br.com.FinanceApp.entity.Lancamento;
import br.com.FinanceApp.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;


    @PostMapping
    public ResponseEntity<LancamentoResponseDto> create (@Valid @RequestBody LancamentoRequestDto dto) {
        return lancamentoService.create(dto);
    }
}
