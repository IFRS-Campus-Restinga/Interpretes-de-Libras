package br.com.interpreto.controller;

import br.com.interpreto.model.solicitacao.SolicitacaoAtualizaDTO;
import br.com.interpreto.model.solicitacao.SolicitacaoCadastroDTO;
import br.com.interpreto.model.solicitacao.SolicitacaoDetalhamentoDTO;
import br.com.interpreto.service.SolicitacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacao")
//Questão de CORS, uso de portas dentro dos navegadores!
@CrossOrigin(origins = "*")
public class SolicitacaoController {
    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoDetalhamentoDTO>> listarSolicitacao() {
        return solicitacaoService.listarSolicitacao();
    }
    @PostMapping
    public ResponseEntity cadastrarSolicitacao(@RequestBody @Valid SolicitacaoCadastroDTO dados, UriComponentsBuilder uriBuilder)
            throws JsonProcessingException{
        return solicitacaoService.cadastrarSolicitacao(dados, uriBuilder);
    }
    @GetMapping("/{id}")
    public ResponseEntity buscarSolicitacao(@PathVariable Long id) {
        return solicitacaoService.buscarSolicitacao(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity atualizarSolicitacao(@PathVariable Long id, @RequestBody @Valid SolicitacaoAtualizaDTO novosDados) {
        return solicitacaoService.atualizarSolicitacao(id, novosDados);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarSolicitacao(@PathVariable Long id) {
        return solicitacaoService.deletarSolicitacao(id);
    }
}