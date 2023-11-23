package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteRepository;
import br.com.interpreto.model.solicitacao.*;
import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoAtualizaDTO;
import br.com.interpreto.model.surdo.SurdoCadastroDTO;
import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {
    final private SolicitacaoRepository solicitacaoRepository;
    final private InterpreteRepository interpreteRepository;

    @Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository, InterpreteRepository interpreteRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.interpreteRepository = interpreteRepository;
    }
    @Transactional
    public ResponseEntity cadastrarSolicitacao(SolicitacaoCadastroDTO dados, UriComponentsBuilder uriBuilder) throws JsonProcessingException{
        Solicitacao solicitacao = new Solicitacao(dados);
        solicitacaoRepository.save(solicitacao);

        var uri = uriBuilder.path("/solicitacao/{id}").buildAndExpand(solicitacao.getId()).toUri();

        return ResponseEntity.created(uri).body(new SolicitacaoDetalhamentoDTO(solicitacao));
    }
    public ResponseEntity<List<SolicitacaoDetalhamentoDTO>> listarSolicitacao() {
        List<Solicitacao> listagem = solicitacaoRepository.findAll();

        List<SolicitacaoDetalhamentoDTO> listagemDTO = new ArrayList<>();
        for (Solicitacao solicitacao: listagem){
            listagemDTO.add(new SolicitacaoDetalhamentoDTO(solicitacao));
        }
        return ResponseEntity.ok(listagemDTO);
    }
    public ResponseEntity buscarSolicitacao(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.getReferenceById(id);

        return ResponseEntity.ok(new SolicitacaoDetalhamentoDTO(solicitacao));
    }
    @Transactional
    public ResponseEntity atualizarSolicitacao(Long id, SolicitacaoAtualizaDTO novosDados) {
        Solicitacao solicitacao = solicitacaoRepository.getReferenceById(id);
        solicitacao.solicitacaoAtualizarDTO(novosDados);
        solicitacaoRepository.save(solicitacao);

        return ResponseEntity.ok(new SolicitacaoDetalhamentoDTO(solicitacao));
    }
    @Transactional
    public ResponseEntity deletarSolicitacao(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.getReferenceById(id);
        solicitacaoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    
	public Optional<Solicitacao> buscarSolicitacaoSurdo(Long id) {
	    return solicitacaoRepository.findById(id);
	}

	// "Intérprete selecionado com sucesso. Status da solicitação atualizado para aguardando_aceite."
	@Transactional
	public ResponseEntity<String> selecionarCandidaturaInterprete(Long solicitacaoId, Long interpreteId) {
		return solicitacaoRepository.findById(solicitacaoId)
				.map(solicitacao -> interpreteRepository.findById(interpreteId).map(interprete -> {
					solicitacao.setInterprete(interprete);
					solicitacao.setStatusSolicitacao(StatusSolicitacao.AGUARDANDO_ACEITE);
					solicitacaoRepository.save(solicitacao);
					return ResponseEntity.ok("Intérprete selecionado com sucesso.");
				}).orElse(ResponseEntity.notFound().build())).orElse(ResponseEntity.notFound().build());
	}
	
	// Interprete - listar Solicitacoes Escolhido .
	public List<SolicitacaoLista> listarSolicitacoesEscolhido(Long interpreteId) {
		List<SolicitacaoLista> solicitacaoLista = solicitacaoRepository.findByInterpreteId(interpreteId).stream()
				.map(SolicitacaoLista::new).collect(Collectors.toList());

		return solicitacaoLista;
	}
	
	public ResponseEntity<String> aceitarSolicitacao(Long solicitacaoId, String status) {
		return solicitacaoRepository.findById(solicitacaoId).map(solicitacao -> {
			if (solicitacao.getStatusSolicitacao() == StatusSolicitacao.AGUARDANDO_ACEITE) {
				solicitacao.setStatusSolicitacao(
						"ACEITAR".equalsIgnoreCase(status) ? StatusSolicitacao.ACEITA : StatusSolicitacao.CANCELADA);
				solicitacaoRepository.save(solicitacao);
				return ResponseEntity.ok("Solicitação " + ("ACEITAR".equalsIgnoreCase(status) ? "aceita" : "recusada")
						+ " com sucesso.");
			}
			return ResponseEntity.badRequest().body("A solicitação não está aguardando aceite.");
		}).orElse((  (BodyBuilder) ResponseEntity.notFound()).body("Solicitação não encontrada."));
	}
}

