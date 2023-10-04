package br.com.banco.dto.contas;

import br.com.banco.enums.TipoConta;
import br.com.banco.model.ContaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ContaResponse {
    private Long id;
    private String numero;
    private double saldo;
    private double fatura;
    private double credito;
    private TipoConta tipo;

    public ContaResponse(ContaModel contaModel) {
        this.id = contaModel.getId();
        this.numero = contaModel.getNumero();
        this.saldo = contaModel.getSaldo();
        this.fatura = contaModel.getFatura();
        this.credito = contaModel.getCredito();
        this.tipo = contaModel.getTipo();
    }
}
