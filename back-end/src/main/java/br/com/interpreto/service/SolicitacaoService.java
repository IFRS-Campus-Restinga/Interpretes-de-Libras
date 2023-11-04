package br.com.interpreto.service;

import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.solicitacao.SolicitacaoAtualizaDTO;
import br.com.interpreto.model.solicitacao.SolicitacaoCadastroDTO;
import br.com.interpreto.model.solicitacao.SolicitacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {
    final private SolicitacaoRepository solicitacaoRepository;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }
    @Transactional
    public void cadastrarSolicitacao(SolicitacaoCadastroDTO dados) {
        solicitacaoRepository.save(new Solicitacao(dados));
    }
    public List<Solicitacao> listarSolicitacao() {
        return solicitacaoRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Solicitacao> buscarSolicitacao(Long id) {
        return solicitacaoRepository.findById(id);
    }
    @Transactional
    public void atualizarSolicitacao(Long id, SolicitacaoAtualizaDTO novosDados) {
        Optional<Solicitacao> opcionalSolicitacao = solicitacaoRepository.findById(id);
        if (opcionalSolicitacao.isPresent()) {
            Solicitacao solicitacao = opcionalSolicitacao.get();
            solicitacao.solicitacaoAtualizarDTO(novosDados);
            solicitacaoRepository.save(solicitacao);
        }
    }
    @Transactional
    public void deletarSolicitacao(Long id) {
        Optional<Solicitacao> opcionalSolicitacao = solicitacaoRepository.findById(id);
        if (opcionalSolicitacao.isPresent()) {
            solicitacaoRepository.deleteById(id);
        }
    }
}

