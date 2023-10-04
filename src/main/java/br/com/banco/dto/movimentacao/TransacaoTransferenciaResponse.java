package br.com.banco.dto.movimentacao;

import br.com.banco.enums.TipoMovimentacao;
import br.com.banco.model.ContaModel;
import br.com.banco.model.MovimentacaoModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@Getter
@Setter
public class TransacaoTransferenciaResponse {
    private Long id;
    private UsuarioTransacaoResponse usuarioOrigem;
    private UsuarioTransacaoResponse usuarioDestino;
    private double valor;
    private LocalDate data;
    private TipoMovimentacao tipo;

    public TransacaoTransferenciaResponse(ContaModel contaOrigemModel, ContaModel contaDestinoModel, MovimentacaoModel movimentacaoOrigem) {
        this.id = movimentacaoOrigem.getId();
        this.valor = movimentacaoOrigem.getValor();
        this.data = movimentacaoOrigem.getData();
        this.tipo = movimentacaoOrigem.getTipo();

        this.usuarioOrigem = UsuarioTransacaoResponse
                .builder()
                .usuario(contaOrigemModel.getUsuarioNome())
                .numero(contaOrigemModel.getNumero())
                .saldo(contaOrigemModel.getSaldo())
                .build();

        this.usuarioDestino = UsuarioTransacaoResponse
                .builder()
                .usuario(contaDestinoModel.getUsuarioNome())
                .numero(contaDestinoModel.getNumero())
                .saldo(contaDestinoModel.getSaldo())
                .build();
    }
}
