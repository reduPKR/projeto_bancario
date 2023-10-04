package br.com.banco.model;

import br.com.banco.enums.TipoMovimentacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "movimentacao")
@Builder
@Getter
public class MovimentacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private double valor;
    @NotNull
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaModel conta;
}
