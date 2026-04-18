package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.CategoriaRequestDto;
import br.com.FinanceApp.dto.CategoriaResponseDto;
import br.com.FinanceApp.service.CategoriaService;
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
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;


    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(@Valid @RequestBody CategoriaRequestDto dto) {
        return categoriaService.create(dto);
    }

    @GetMapping
    public Page<CategoriaResponseDto> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return categoriaService.findAll(pageable);
    }

}
