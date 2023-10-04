package br.com.banco.dto.movimentacao;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UsuarioTransacaoResponse {
    private String usuario;
    private String numero;
    private double saldo;
}
