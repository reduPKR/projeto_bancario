package br.com.banco.service;

import br.com.banco.model.ContaModel;
import br.com.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository repository) {
        this.contaRepository = repository;
    }

    public void save(ContaModel contaModel){
        contaRepository.save(contaModel);
    }

    public ContaModel findById(Long id) {
        Optional<ContaModel> optional = contaRepository.findById(id);
        return optional.orElse(null);
    }
}
