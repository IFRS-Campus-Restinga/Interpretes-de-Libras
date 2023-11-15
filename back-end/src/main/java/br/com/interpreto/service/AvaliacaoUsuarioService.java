package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.*;
import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.solicitacao.SolicitacaoAtualizaDTO;
import br.com.interpreto.model.solicitacao.SolicitacaoDetalhamentoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoUsuarioService {
    final private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public AvaliacaoUsuarioService(AvaliacaoUsuarioRepository avaliacaoUsuarioRepository) {
        this.avaliacaoUsuarioRepository = avaliacaoUsuarioRepository;
    }
    @Transactional
    public ResponseEntity cadastrarAvaliacaoUsuario(AvaliacaoUsuarioCadastroDTO dados, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
        AvaliacaoUsuario avaliacaoUsuario = new AvaliacaoUsuario(dados);
        avaliacaoUsuarioRepository.save(avaliacaoUsuario);

        var uri = uriBuilder.path("/avaliacaousuario/{id}").buildAndExpand(avaliacaoUsuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new AvaliacaoUsuarioDetalhamentoDTO(avaliacaoUsuario));
    }
    public ResponseEntity<List<AvaliacaoUsuarioDetalhamentoDTO>> listarAvaliacaoUsuario() {
        List<AvaliacaoUsuario> listagem = avaliacaoUsuarioRepository.findAll();

        List<AvaliacaoUsuarioDetalhamentoDTO> listagemDTO = new ArrayList<>();
        for (AvaliacaoUsuario avaliacaoUsuario: listagem){
            listagemDTO.add(new AvaliacaoUsuarioDetalhamentoDTO(avaliacaoUsuario));
        }
        return ResponseEntity.ok(listagemDTO);
    }
    public ResponseEntity buscarAvaliacaoUsuario(Long id) {
        AvaliacaoUsuario avaliacaoUsuario = avaliacaoUsuarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new AvaliacaoUsuarioDetalhamentoDTO(avaliacaoUsuario));
    }
    @Transactional
    public ResponseEntity atualizarAvaliacaoUsuario(Long id, AvaliacaoUsuarioAtualizaDTO novosDados) {
        AvaliacaoUsuario avaliacaoUsuario = avaliacaoUsuarioRepository.getReferenceById(id);
        avaliacaoUsuario.avaliacaoUsuarioAtualizarDTO(novosDados);
        avaliacaoUsuarioRepository.save(avaliacaoUsuario);

        return ResponseEntity.ok(new AvaliacaoUsuarioDetalhamentoDTO(avaliacaoUsuario));
    }
    @Transactional
    public ResponseEntity deletarAvaliacaoUsuario(Long id) {
        AvaliacaoUsuario avaliacaoUsuario = avaliacaoUsuarioRepository.getReferenceById(id);
        avaliacaoUsuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public Optional<AvaliacaoUsuario> ReceberResultadoSolicitacaoCadastro(Long id) {
        return avaliacaoUsuarioRepository.findById(id);
    }
}