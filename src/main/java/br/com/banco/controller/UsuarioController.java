package br.com.banco.controller;

import br.com.banco.dto.usuario.UsuarioRequest;
import br.com.banco.dto.usuario.UsuarioResponse;
import br.com.banco.dto.usuario.UsuarioVisualizarResponse;
import br.com.banco.model.UsuarioModel;
import br.com.banco.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar(@Validated @RequestBody UsuarioRequest request){
        UsuarioModel usuarioModel = request.getModel();

        service.salvar(usuarioModel);
        UsuarioResponse response = new UsuarioResponse(usuarioModel);

        if (response.getId() != 0){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioVisualizarResponse> visualizarPorId(@PathVariable Long id){
        UsuarioModel usuarioModel = service.findById(id);

        if (usuarioModel != null){
            UsuarioVisualizarResponse response = new UsuarioVisualizarResponse(usuarioModel);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
