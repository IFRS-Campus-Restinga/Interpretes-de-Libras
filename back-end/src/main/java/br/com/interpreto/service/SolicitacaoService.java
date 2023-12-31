package br.com.interpreto.service;

import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteDetalhamentoDTO;
import br.com.interpreto.model.interprete.InterpreteRepository;
import br.com.interpreto.model.solicitacao.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacaoRepository solicitacaoRepository;
    @Autowired
    private InterpreteRepository interpreteRepository;
    @Autowired
    private InterpreteService interpreteService;

    @Transactional
    public ResponseEntity cadastrarSolicitacao(SolicitacaoCadastroDTO dados, UriComponentsBuilder uriBuilder) throws
            JsonProcessingException{
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
    //Metodo usado por SurdoService para buscar Solicitacoes vinculadas a um determinado Surdo, bem como os interpretes que
    //Possuem determinada regiao/especialidade, alterado conforme o Front pediu.
	public ResponseEntity<Map<Object, Object>> buscarSolicitacoes(Long id) {
		Map<Object, Object> resposta = new HashMap<>();
		List<Map<Object, Object>> listagemSolicitacoesComInterpretes = new ArrayList<>();

		List<Solicitacao> listagemSolicitacoes = solicitacaoRepository.findBySurdoId(id);

		for (Solicitacao solicitacao : listagemSolicitacoes) {
			List<InterpreteDetalhamentoDTO> listaInterpretes = new ArrayList<>();

			if (solicitacao.getRegioes() == null) {
				listaInterpretes = interpreteService.listarInterpretesSolicitacaoEspecialidade(solicitacao.getEspecialidade());
			} else if (solicitacao.getEspecialidade() == null) {
				listaInterpretes = interpreteService.listarInterpretesSolicitacaoRegiao(solicitacao.getRegioes());
			} else {
				listaInterpretes = interpreteService.listarInterpretesSolicitacao(solicitacao.getRegioes(), solicitacao.getEspecialidade());
			}

			// Criar um mapa para armazenar a solicitação e a lista de intérpretes
			Map<Object, Object> solicitacaoComInterprete = new HashMap<>();
			solicitacaoComInterprete.put("solicitacao", solicitacao);
			solicitacaoComInterprete.put("listaInterpretes", listaInterpretes);

			listagemSolicitacoesComInterpretes.add(solicitacaoComInterprete);
		}

		resposta.put("solicitacoesComInterpretes", listagemSolicitacoesComInterpretes);
		return ResponseEntity.ok().body(resposta);
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
	
	public ResponseEntity<String> cancelarSolicitacao(Long id) {
        return solicitacaoRepository.findById(id)
                .map(solicitacao -> {
                    if (solicitacao.getStatusSolicitacao() != StatusSolicitacao.ABERTA &&
                            solicitacao.getStatusSolicitacao() != StatusSolicitacao.AGUARDANDO_ACEITE) {
                        return ResponseEntity.badRequest().body("Solicitação não pode ser cancelada no estado atual");
                    }

                    solicitacao.setStatusSolicitacao(StatusSolicitacao.CANCELADA);
                    solicitacaoRepository.save(solicitacao);
                    return ResponseEntity.ok("Solicitação cancelada com sucesso");
                })
                .orElse(((BodyBuilder) ResponseEntity.notFound()).body("Solicitação não encontrada"));
    }
	
	public ResponseEntity<String> selecionarCandidatura(Long solicitacaoId, Long interpreteId) {
		return solicitacaoRepository.findById(solicitacaoId)
				.map(solicitacao -> interpreteRepository.findById(interpreteId).map(interprete -> {
					solicitacao.setInterprete(interprete);
					solicitacao.setStatusSolicitacao(StatusSolicitacao.ACEITA);
					solicitacaoRepository.save(solicitacao);
					return ResponseEntity.ok("Intérprete selecionado com sucesso.");
				}).orElse(ResponseEntity.notFound().build())).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<String> alterarStatusSolicitacao(Long id, StatusSolicitacao statusAtual,
			StatusSolicitacao novoStatus) {
		return solicitacaoRepository.findById(id).map(solicitacao -> {
			if (solicitacao.getStatusSolicitacao() != statusAtual) {
				return ResponseEntity.badRequest().body("Solicitação não pode ter o status alterado no estado atual");
			}

			solicitacao.setStatusSolicitacao(novoStatus);
			solicitacaoRepository.save(solicitacao);

			if (novoStatus != null) {
				return ResponseEntity.ok("Solicitação alterada para " + novoStatus);
			} else {
				return ResponseEntity.ok("Solicitação finalizada");
			}
		}).orElse(ResponseEntity.notFound().build());
	}

	public List<SolicitacaoAvaliacao> aguardandoAvaliacao() {
		List<Solicitacao> solicitacoes = solicitacaoRepository
				.findByStatusSolicitacao(StatusSolicitacao.AGUARDANDO_AVALIACAO);
		return solicitacoes.stream().map(SolicitacaoAvaliacao::new).collect(Collectors.toList());
	}

	public SolicitacaoAvaliacao aguardandoAvaliacaoPorId(Long solicitacaoId) {
		Optional<Solicitacao> optionalSolicitacao = solicitacaoRepository.findByIdAndStatusSolicitacao(solicitacaoId,
				StatusSolicitacao.AGUARDANDO_AVALIACAO);

		return optionalSolicitacao.map(SolicitacaoAvaliacao::new).orElse(null);
	}

}

