package br.com.banco.dto.usuario;

import br.com.banco.model.UsuarioModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Setter
@Getter
public class UsuarioRequest {
    @NotEmpty
    @Size(max = 100)
    private String nome;

    @NotEmpty
    @Size(max = 6)
    private String senha;

    @NotNull
    private LocalDate dataNascimento;

    public UsuarioModel getModel() {
        return UsuarioModel
                .builder()
                .nome(this.nome)
                .dataNascimento(this.dataNascimento)
                .senha(this.senha)
                .build();
    }
}
