package br.com.FinanceApp.repository;

import br.com.FinanceApp.entity.Lancamento;
import br.com.FinanceApp.entity.TipoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.Optional;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    @Query(
        "SELECT l FROM Lancamento l WHERE l.usuario.id = :id"
    )
    Page<Lancamento> findAllFilteredByUser(Pageable pageable, Long id);

    @Query(
            """
        SELECT SUM(l.valor) FROM Lancamento l WHERE l.usuario.id = :id
                AND l.tipo = :tipoLancamento
                    AND MONTH(l.dataLancamento) = MONTH(CURRENT_DATE)
                            AND YEAR(l.dataLancamento) = YEAR(CURRENT_DATE)
        """
    )
    Optional<BigDecimal> sumLancamentoByUsuario(Long id, TipoLancamento tipoLancamento);
}
