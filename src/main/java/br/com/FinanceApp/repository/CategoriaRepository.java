package br.com.FinanceApp.repository;

import br.com.FinanceApp.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(
            "SELECT c FROM Categoria c WHERE c.usuario.id = :idUsuario"
    )
    Page<Categoria> findAllFilteredByUser(Pageable pageable, Long idUsuario);
}
