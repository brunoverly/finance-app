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
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime dataLancamento;
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;


    @PrePersist
    public void prePersist() {
        this.dataLancamento = LocalDateTime.now();
    }
}




