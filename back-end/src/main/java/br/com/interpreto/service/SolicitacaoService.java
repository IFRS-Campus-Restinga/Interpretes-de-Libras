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
        //Para armazenar interpretes
        List<InterpreteDetalhamentoDTO> listaInterpretes = new ArrayList<>();
        //É criada a Solicitacao
        Solicitacao solicitacao = new Solicitacao(dados);
        solicitacaoRepository.save(solicitacao);

        //Verifico se regioes ou especialidades são nulas, casa verdadeiro crio uma Solicitacao Publica e retorno pro front essa Solicitacao
        if(dados.regioes() == null && dados.especialidades() == null){
            var uri = uriBuilder.path("/solicitacao/{id}").buildAndExpand(solicitacao.getId()).toUri();

            return ResponseEntity.created(uri).body(new SolicitacaoDetalhamentoDTO(solicitacao));
        }
        if(dados.regioes() == null){
            listaInterpretes = interpreteService.listarInterpretesSolicitacaoEspecialidade(dados.especialidades());
        }
        if(dados.especialidades() == null){
            listaInterpretes = interpreteService.listarInterpretesSolicitacaoRegiao(dados.regioes());
        }
        if(dados.regioes() != null && dados.especialidades() != null){
            listaInterpretes = interpreteService.listarInterpretesSolicitacao(dados.regioes(), dados.especialidades());
        }
        //Lista vazia, não encontrou interpretes então será criada uma Solicitacao Publica e retorno pro front essa Solicitacao!
        if(listaInterpretes.isEmpty()){
            var uri = uriBuilder.path("/solicitacao/{id}").buildAndExpand(solicitacao.getId()).toUri();

            return ResponseEntity.created(uri).body(new SolicitacaoDetalhamentoDTO(solicitacao));
        }
        //Lista com Interpretes, então retorno para o front essa lista de interpretes e o id da solicitacao para depois dar um PUT
        //Na Solicitacao agora com um Interprete selecionado!
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("solicitacaoId", solicitacao.getId());
        resposta.put("listaInterpretes", listaInterpretes);

        return ResponseEntity.ok().body(resposta);
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
    //Metodo usado por SurdoService para buscar Solicitacoes vinculadas a um determinado Surdo
    public ResponseEntity<List<SolicitacaoDetalhamentoDTO>> buscarSolicitacoes (Long id){
        List<Solicitacao> listagem = solicitacaoRepository.findBySurdoId(id);

        List<SolicitacaoDetalhamentoDTO> listagemDTO = new ArrayList<>();
        for (Solicitacao solicitacao: listagem){
            listagemDTO.add(new SolicitacaoDetalhamentoDTO(solicitacao));
        }
        return ResponseEntity.ok(listagemDTO);
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
}

