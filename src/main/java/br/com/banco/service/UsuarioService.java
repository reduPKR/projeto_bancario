package br.com.banco.service;

import br.com.banco.model.UsuarioModel;
import br.com.banco.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvar(UsuarioModel usuarioModel) {
        repository.save(usuarioModel);
    }

    public UsuarioModel findById(Long id) {
        Optional<UsuarioModel> optional = repository.findById(id);

        return optional.orElse(null);
    }
}
