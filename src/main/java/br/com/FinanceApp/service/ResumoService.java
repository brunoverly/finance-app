package br.com.FinanceApp.service;


import br.com.FinanceApp.controller.ResmoController;
import br.com.FinanceApp.dto.ResumoResponseDto;
import br.com.FinanceApp.entity.Lancamento;
import br.com.FinanceApp.entity.TipoLancamento;
import br.com.FinanceApp.entity.Usuario;
import br.com.FinanceApp.repository.LancamentoRepository;
import br.com.FinanceApp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;

@Service
public class ResumoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LancamentoRepository lancamentoRepository;



    public ResponseEntity<ResumoResponseDto> getResumo() {
        BigDecimal receita = BigDecimal.ZERO;
        BigDecimal despesa = BigDecimal.ZERO;

        Usuario usuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Lancamento> lancamentos = lancamentoRepository.findAllFilteredByUser(usuario.getId());
        for (Lancamento lancamento : lancamentos) {
            if(lancamento.getTipo().equals(TipoLancamento.RECEITA)) receita = receita.add(lancamento.getValor());
            if(lancamento.getTipo().equals(TipoLancamento.DESPESA)) despesa = despesa.add(lancamento.getValor());
        }

        BigDecimal saldo = receita.subtract(despesa);
        ResumoResponseDto resumo = new ResumoResponseDto(receita, despesa, saldo);

        return ResponseEntity.ok(resumo);
    }
}
