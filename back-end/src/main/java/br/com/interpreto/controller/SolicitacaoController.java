package br.com.interpreto.controller;

import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.solicitacao.SolicitacaoAtualizaDTO;
import br.com.interpreto.model.solicitacao.SolicitacaoCadastroDTO;
import br.com.interpreto.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacao")
//Questão de CORS, uso de portas dentro dos navegadores!
@CrossOrigin(origins = "*")
public class SolicitacaoController {
    private final SolicitacaoService solicitacaoService;
    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }
    @GetMapping
    public List<Solicitacao> listarSolicitacao() {
        return solicitacaoService.listarSolicitacao();
    }
    @PostMapping
    public void cadastrarSolicitacao(@RequestBody @Valid SolicitacaoCadastroDTO dados) {
        solicitacaoService.cadastrarSolicitacao(dados);
    }
    @GetMapping("/{id}")
    public Optional<Solicitacao> buscarSolicitacao(@PathVariable Long id) {
        return solicitacaoService.buscarSolicitacao(id);
    }
    @PutMapping("/{id}")
    public void atualizarSolicitacao(@PathVariable Long id, @RequestBody @Valid SolicitacaoAtualizaDTO novosDados) {
        solicitacaoService.atualizarSolicitacao(id, novosDados);
    }
    @DeleteMapping("/{id}")
    public void deletarSolicitacao(@PathVariable Long id) {
        solicitacaoService.deletarSolicitacao(id);
    }
}