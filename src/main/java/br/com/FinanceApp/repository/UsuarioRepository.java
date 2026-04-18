package br.com.FinanceApp.repository;

import br.com.FinanceApp.entity.Usuario;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String subject);

    boolean existsByEmail(String email);
}
