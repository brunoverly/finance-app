package br.com.FinanceApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "lancamentos")
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;
    BigDecimal valor;
    LocalDateTime dataLancamento;
    @Enumerated(EnumType.STRING)
    TipoLancamento tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    Usuario usuario;


    @PrePersist
    public void prePersist() {
        this.dataLancamento = LocalDateTime.now();
    }
}




