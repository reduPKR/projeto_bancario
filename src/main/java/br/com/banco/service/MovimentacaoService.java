package br.com.banco.service;

import br.com.banco.model.MovimentacaoModel;
import br.com.banco.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public void save(MovimentacaoModel movimentacaoModel) {
        movimentacaoRepository.save(movimentacaoModel);
    }
}
