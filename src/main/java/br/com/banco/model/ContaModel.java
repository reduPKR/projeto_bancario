package br.com.banco.model;

import br.com.banco.enums.TipoConta;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "conta")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private double saldo;

    @Column(nullable = false)
    private double fatura;

    @Column(nullable = false)
    private double credito;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovimentacaoModel> movimentacoes;

    public void addMovimentacao(MovimentacaoModel movimentacaoModel){
        this.movimentacoes.add(movimentacaoModel);
    }

    public String getUsuarioNome() {
        return this.usuario.getNome();
    }

    public void depositar(double valor) {
        this.saldo = saldo + valor;
    }

    public void sangria(double valor) {
        this.saldo = saldo - valor;
    }
}
