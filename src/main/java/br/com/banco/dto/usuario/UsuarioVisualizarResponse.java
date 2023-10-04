package br.com.banco.dto.usuario;

import br.com.banco.dto.contas.ContaResponse;
import br.com.banco.model.UsuarioModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@Getter
public class UsuarioVisualizarResponse {
    private Long id;
    private String nome;
    private String senha;
    private LocalDate dataNascimento;
    private List<ContaResponse> contas;

    public UsuarioVisualizarResponse(UsuarioModel model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.senha = model.getSenha();
        this.dataNascimento = model.getDataNascimento();

        this.contas = model
                .getContas()
                .stream()
                .map(conta -> new ContaResponse(
                            conta.getId(),
                            conta.getNumero(),
                            conta.getSaldo(),
                            conta.getFatura(),
                            conta.getCredito(),
                            conta.getTipo()
                    ))
                .collect(Collectors.toList());
    }
}
