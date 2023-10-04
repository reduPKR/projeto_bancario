package br.com.banco.dto.contas;

import br.com.banco.enums.TipoConta;
import br.com.banco.model.ContaModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ContaRequest {
    @NotNull
    private Long usuario_id;
    @NotEmpty
    private String numero;
    @NotNull
    private double saldo;
    @NotNull
    private double fatura;
    @NotNull
    private double credito;
    @NotNull
    private TipoConta tipo;

    public ContaModel getModel() {
        return ContaModel
                .builder()
                .numero(this.numero)
                .saldo(this.saldo)
                .fatura(this.fatura)
                .credito(this.credito)
                .tipo(this.tipo)
                .build();
    }
}
