package br.com.FinanceApp.controller;


import br.com.FinanceApp.dto.ResumoResponseDto;
import br.com.FinanceApp.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResmoController {
    @Autowired
    private ResumoService resumoService;

    @GetMapping
    public ResponseEntity<ResumoResponseDto> getResumo() {
        return resumoService.getResumo();
    }
}
