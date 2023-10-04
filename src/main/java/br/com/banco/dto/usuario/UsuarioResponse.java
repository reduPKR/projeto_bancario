package br.com.banco.dto.usuario;

import br.com.banco.model.UsuarioModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@Getter
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String senha;
    private LocalDate dataNascimento;

    public UsuarioResponse(UsuarioModel usuarioModel) {
        this.id = usuarioModel.getId();
        this.nome = usuarioModel.getNome();
        this.senha = usuarioModel.getSenha();
        this.dataNascimento = usuarioModel.getDataNascimento();
    }
}
