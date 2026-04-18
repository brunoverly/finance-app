package br.com.FinanceApp.controller;

import br.com.FinanceApp.dto.CategoriaRequestDto;
import br.com.FinanceApp.dto.CategoriaResponseDto;
import br.com.FinanceApp.entity.Categoria;
import br.com.FinanceApp.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;


    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(@Valid @RequestBody CategoriaRequestDto dto) {
        return categoriaService.create(dto);
    }

}
