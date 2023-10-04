package br.com.banco.controller;

import br.com.banco.dto.movimentacao.TransacaoDepositoRequest;
import br.com.banco.dto.movimentacao.TransacaoDepositoResponse;
import br.com.banco.dto.movimentacao.TransacaoTransferenciaResponse;
import br.com.banco.dto.movimentacao.TransacaoTransferirRequest;
import br.com.banco.enums.TipoMovimentacao;
import br.com.banco.model.ContaModel;
import br.com.banco.model.MovimentacaoModel;
import br.com.banco.model.UsuarioModel;
import br.com.banco.service.ContaService;
import br.com.banco.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final MovimentacaoService movimentacaoService;
    private final ContaService contaService;

    @Autowired
    public TransacaoController(MovimentacaoService movimentacaoService, ContaService contaService) {
        this.movimentacaoService = movimentacaoService;
        this.contaService = contaService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<TransacaoDepositoResponse> receber(@Validated @RequestBody TransacaoDepositoRequest request){
        ContaModel contaModel = contaService.findById(request.getConta_id());

        if (contaModel != null){
            MovimentacaoModel movimentacaoModel =
                    MovimentacaoModel
                            .builder()
                            .valor(request.getValor())
                            .tipo(TipoMovimentacao.ENTRADA)
                            .data(LocalDate.now())
                            .conta(contaModel)
                            .build();

            movimentacaoService.save(movimentacaoModel);

            if (movimentacaoModel.getId() != 0){
                contaModel.depositar(movimentacaoModel.getValor());
                contaModel.addMovimentacao(movimentacaoModel);
                contaService.save(contaModel);

                TransacaoDepositoResponse response = new TransacaoDepositoResponse(contaModel, movimentacaoModel);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/pagamento")
    public ResponseEntity<TransacaoTransferenciaResponse> transferir(@Validated @RequestBody TransacaoTransferirRequest request){
        ContaModel contaOrigemModel = contaService.findById(request.getConta_origem_id());
        ContaModel contaDestinoModel = contaService.findById(request.getConta_destino_id());

        if (contaOrigemModel != null && contaDestinoModel != null){
            MovimentacaoModel movimentacaoOrigem =
                    MovimentacaoModel
                            .builder()
                            .valor(request.getValor())
                            .tipo(TipoMovimentacao.SAIDA)
                            .data(LocalDate.now())
                            .conta(contaOrigemModel)
                            .build();

            MovimentacaoModel movimentacaoDestino =
                    MovimentacaoModel
                            .builder()
                            .valor(request.getValor())
                            .tipo(TipoMovimentacao.ENTRADA)
                            .data(LocalDate.now())
                            .conta(contaDestinoModel)
                            .build();

            movimentacaoService.save(movimentacaoOrigem);
            movimentacaoService.save(movimentacaoDestino);

            if (movimentacaoOrigem.getId() != 0 && movimentacaoDestino.getId() != 0){
                contaOrigemModel.sangria(movimentacaoOrigem.getValor());
                contaOrigemModel.addMovimentacao(movimentacaoOrigem);

                contaDestinoModel.depositar(movimentacaoDestino.getValor());
                contaDestinoModel.addMovimentacao(movimentacaoDestino);

                contaService.save(contaOrigemModel);
                contaService.save(contaDestinoModel);

                TransacaoTransferenciaResponse response = new TransacaoTransferenciaResponse(
                        contaOrigemModel,
                        contaDestinoModel,
                        movimentacaoOrigem
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
