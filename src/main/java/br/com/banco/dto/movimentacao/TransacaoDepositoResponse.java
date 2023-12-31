package br.com.banco.dto.movimentacao;

import br.com.banco.enums.TipoMovimentacao;
import br.com.banco.model.ContaModel;
import br.com.banco.model.MovimentacaoModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@Getter
@Setter
public class TransacaoDepositoResponse {
    private Long id;
    private UsuarioTransacaoResponse usuario;
    private String numero;
    private double saldo;
    private double valor;
    private LocalDate data;
    private TipoMovimentacao tipo;

    public TransacaoDepositoResponse(ContaModel contaModel, MovimentacaoModel movimentacaoModel) {
        this.id = movimentacaoModel.getId();
        this.valor = movimentacaoModel.getValor();
        this.data = movimentacaoModel.getData();
        this.tipo = movimentacaoModel.getTipo();

        this.usuario = UsuarioTransacaoResponse
                .builder()
                .usuario(contaModel.getUsuarioNome())
                .numero(contaModel.getNumero())
                .saldo(contaModel.getSaldo())
                .build();
    }
}
