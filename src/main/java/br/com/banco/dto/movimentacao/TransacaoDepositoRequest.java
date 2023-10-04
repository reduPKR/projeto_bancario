package br.com.banco.dto.movimentacao;

import br.com.banco.enums.TipoMovimentacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
public class TransacaoDepositoRequest {
    @NotNull
    private Long conta_id;
    @NotNull
    private double valor;
}
