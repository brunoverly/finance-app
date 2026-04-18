package br.com.FinanceApp.repository;

import br.com.FinanceApp.entity.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    @Query(
        "SELECT l FROM Lancamento l WHERE l.usuario.id = :id"
    )
    Page<Lancamento> findAllFilteredByUser(Pageable pageable, Long id);

    @Query(
            "SELECT l FROM Lancamento l WHERE l.usuario.id = :id"
    )
    List<Lancamento> findAllFilteredByUser(Long id);
}
