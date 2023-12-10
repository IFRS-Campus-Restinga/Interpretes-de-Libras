package br.com.interpreto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.interpreto.model.interprete.InterpreteDetalhamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.interpreto.model.candidatura.Candidatura;
import br.com.interpreto.model.candidatura.CandidaturaRepository;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteRepository;
import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.solicitacao.SolicitacaoRepository;

@Service
public class CandidaturaService {
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private InterpreteRepository interpreteRepository;
	@Autowired
	private CandidaturaRepository candidaturaRepository;

	// Método para buscar todas as candidaturas
	public List<Candidatura> buscarTodasCandidaturas() {
		return candidaturaRepository.findAll();
	}

	// Método para buscar uma candidatura por ID
	public Optional<Candidatura> buscarCandidaturaPorId(Long id) {
		return candidaturaRepository.findById(id);
	}

	// Método para atualizar uma candidatura
	public Candidatura atualizarCandidatura(Long id, Candidatura candidaturaAtualizada) {
		// Verifica se a candidatura existe
		if (candidaturaRepository.existsById(id)) {
			// Define o ID da candidatura atualizada
			candidaturaAtualizada.setId(id);
			// Salva a candidatura atualizada
			return candidaturaRepository.save(candidaturaAtualizada);
		} else {
			// Lidar com a situação em que a candidatura não existe
			return null;
		}
	}

	// Método para excluir uma candidatura por ID
	public void deletarCandidatura(Long id) {
		candidaturaRepository.deleteById(id);
	}

	public ResponseEntity<String> candidatarSolicitacao(Long solicitacaoId, Long interpreteId, Double novoValorHora) {
		// Verificar se a solicitação e o intérprete existem
		Solicitacao solicitacao = solicitacaoRepository.findById(solicitacaoId).orElse(null);
		Interprete interprete = interpreteRepository.findById(interpreteId).orElse(null);

		if (solicitacao == null || interprete == null) {
			// Lidar com o caso em que a solicitação ou o intérprete não existem
			return new ResponseEntity<>("Solicitação ou intérprete não encontrado", HttpStatus.NOT_FOUND);
		}

		// Verificar se já existe uma candidatura para esta solicitação e intérprete
		if (candidaturaRepository.existsBySolicitacaoAndInterprete(solicitacao, interprete)) {
			// Lidar com o caso em que já existe uma candidatura
			return new ResponseEntity<>("Já existe uma candidatura para esta solicitação e intérprete",
					HttpStatus.CONFLICT);
		}

		// Atualizar o valor da hora do intérprete
		interprete.setValorHora(novoValorHora);

		// Criar uma nova candidatura
		Candidatura candidatura = new Candidatura(solicitacao, interprete);
		candidaturaRepository.save(candidatura);

		return new ResponseEntity<>("Candidatura realizada com sucesso", HttpStatus.OK);
	}

	// Método para salvar uma nova candidatura
	public Candidatura salvarCandidatura(Candidatura candidatura) {
		return candidaturaRepository.save(candidatura);
	}

	//Lista candidaturas de interpretes a uma solitação
	public ResponseEntity<List<InterpreteDetalhamentoDTO>> listarCandidaturasInterprete(Long solicitacaoId) {

		List<Candidatura> candidaturas = candidaturaRepository.findAll();
		List<InterpreteDetalhamentoDTO> listagemDTO = new ArrayList<>();

		for (Candidatura candidatura: candidaturas) {
			if(candidatura.getSolicitacao().getId().equals(solicitacaoId)){
				listagemDTO.add(new InterpreteDetalhamentoDTO(candidatura.getInterprete()));
			}
		}
		return ResponseEntity.ok(listagemDTO);
	}


	// Adicione outros métodos conforme necessário para atender aos requisitos do
	// seu aplicativo
}
