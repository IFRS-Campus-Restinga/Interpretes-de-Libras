package br.com.interpreto.controller;


import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioAtualizaDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.service.AvaliacaoUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacaousuario")
public class AvaliacaoUsuarioController {
    private final AvaliacaoUsuarioService avaliacaoUsuarioService;

    @Autowired
    public AvaliacaoUsuarioController(AvaliacaoUsuarioService avaliacaoUsuarioService) {
        this.avaliacaoUsuarioService = avaliacaoUsuarioService;
    }
    @GetMapping
    public List<AvaliacaoUsuario> listarAvaliacao() {
        return avaliacaoUsuarioService.listarAvaliacaoUsuario();
    }


    @PostMapping
    public void cadastrarAvaliacaoUsuario(@RequestBody @Valid AvaliacaoUsuarioCadastroDTO dados) {
        avaliacaoUsuarioService.cadastrarAvaliacaoUsuario(dados);
    }
    @GetMapping("/{id}")
    public Optional<AvaliacaoUsuario> buscarAvaliacaoUsuario(@PathVariable Long id) {
        return avaliacaoUsuarioService.buscarAvaliacaoUsuario(id);
    }
    @PutMapping("/{id}")
    public void atualizarAvaliacaoUsuario(@PathVariable Long id, @RequestBody @Valid AvaliacaoUsuarioAtualizaDTO novosDados) {
        avaliacaoUsuarioService.atualizarAvaliacaoUsuario(id, novosDados);
    }
    @DeleteMapping("/{id}")
    public void deletarAvaliacaoUsuario(@PathVariable Long id) {
        avaliacaoUsuarioService.deletarAvaliacaoUsuario(id);
    }
}
