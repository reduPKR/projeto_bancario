package br.com.banco.controller;

import br.com.banco.dto.contas.ContaRequest;
import br.com.banco.dto.contas.ContaResponse;
import br.com.banco.model.ContaModel;
import br.com.banco.model.UsuarioModel;
import br.com.banco.service.ContaService;
import br.com.banco.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaService contaService;
    private final UsuarioService usuarioService;

    @Autowired
    public ContaController(ContaService contaService, UsuarioService usuarioService) {
        this.contaService = contaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ContaResponse> salvar(@Validated @RequestBody ContaRequest request){
        UsuarioModel usuarioModel = usuarioService.findById(request.getUsuario_id());

        if (usuarioModel != null) {
            ContaModel contaModel = request.getModel();
            contaModel.setUsuario(usuarioModel);

            contaService.save(contaModel);

            if (contaModel.getId() != 0){
                usuarioModel.addConta(contaModel);

                ContaResponse response = new ContaResponse(contaModel);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
