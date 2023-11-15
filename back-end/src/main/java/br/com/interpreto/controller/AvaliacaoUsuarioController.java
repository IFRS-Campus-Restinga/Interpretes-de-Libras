package br.com.interpreto.controller;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioAtualizaDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioDetalhamentoDTO;
import br.com.interpreto.service.AvaliacaoUsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/avaliacaousuario")
//Questão de CORS, uso de portas dentro dos navegadores!
@CrossOrigin(origins = "*")
public class AvaliacaoUsuarioController {
    private final AvaliacaoUsuarioService avaliacaoUsuarioService;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public AvaliacaoUsuarioController(AvaliacaoUsuarioService avaliacaoUsuarioService) {
        this.avaliacaoUsuarioService = avaliacaoUsuarioService;
    }
    @GetMapping
    public ResponseEntity<List<AvaliacaoUsuarioDetalhamentoDTO>> listarAvaliacaoUsuario() {
        return avaliacaoUsuarioService.listarAvaliacaoUsuario();
    }
    @PostMapping
    public ResponseEntity cadastrarAvaliacaoUsuario(@RequestBody @Valid AvaliacaoUsuarioCadastroDTO dados, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
        return avaliacaoUsuarioService.cadastrarAvaliacaoUsuario(dados, uriBuilder);
    }
    @GetMapping("/{id}")
    public ResponseEntity buscarAvaliacaoUsuario(@PathVariable Long id) {
        return avaliacaoUsuarioService.buscarAvaliacaoUsuario(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity atualizarAvaliacaoUsuario(@PathVariable Long id, @RequestBody @Valid AvaliacaoUsuarioAtualizaDTO novosDados) {
        return avaliacaoUsuarioService.atualizarAvaliacaoUsuario(id, novosDados);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarAvaliacaoUsuario(@PathVariable Long id) {
        return avaliacaoUsuarioService.deletarAvaliacaoUsuario(id);
    }
}
