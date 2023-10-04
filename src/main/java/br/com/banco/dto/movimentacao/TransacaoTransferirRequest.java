package br.com.banco.dto.movimentacao;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TransacaoTransferirRequest {
    @NotNull
    private Long conta_origem_id;
    @NotNull
    private Long conta_destino_id;
    @NotNull
    private double valor;
}
