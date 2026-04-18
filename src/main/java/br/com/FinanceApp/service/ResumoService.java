package br.com.FinanceApp.service;


import br.com.FinanceApp.dto.ResumoMensalDto;
import br.com.FinanceApp.entity.TipoLancamento;
import br.com.FinanceApp.entity.Usuario;
import br.com.FinanceApp.repository.LancamentoRepository;
import br.com.FinanceApp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ResumoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LancamentoRepository lancamentoRepository;



    public ResponseEntity<ResumoMensalDto> getResumo() {
        Usuario usuario = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        BigDecimal receita = lancamentoRepository.sumLancamentoByUsuario(usuario.getId(), TipoLancamento.RECEITA).orElse(BigDecimal.ZERO);
        BigDecimal despesa = lancamentoRepository.sumLancamentoByUsuario(usuario.getId(), TipoLancamento.DESPESA).orElse(BigDecimal.ZERO);
        BigDecimal saldo = receita.subtract(despesa);
        ResumoMensalDto resumo = new ResumoMensalDto(receita, despesa, saldo);

        return ResponseEntity.ok(resumo);
    }
}
