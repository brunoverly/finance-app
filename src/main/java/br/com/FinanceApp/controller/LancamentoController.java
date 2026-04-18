package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.LancamentoRequestDto;
import br.com.FinanceApp.dto.LancamentoResponseDto;
import br.com.FinanceApp.entity.Lancamento;
import br.com.FinanceApp.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;


    @PostMapping
    public ResponseEntity<LancamentoResponseDto> create (@Valid @RequestBody LancamentoRequestDto dto) {
        return lancamentoService.create(dto);
    }

    @GetMapping
    public Page<LancamentoResponseDto> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return lancamentoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoResponseDto> findById(@PathVariable Long id) {
        return lancamentoService.findById(id);
    }
}
