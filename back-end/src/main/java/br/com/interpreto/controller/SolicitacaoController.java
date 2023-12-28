package br.com.interpreto.controller;

import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.solicitacao.SolicitacaoAtualizaDTO;
import br.com.interpreto.model.solicitacao.SolicitacaoAvaliacao;
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
	public ResponseEntity cadastrarSolicitacao(@RequestBody @Valid SolicitacaoCadastroDTO dados,
			UriComponentsBuilder uriBuilder) throws JsonProcessingException {
		return solicitacaoService.cadastrarSolicitacao(dados, uriBuilder);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarSolicitacao(@PathVariable Long id) {
		return solicitacaoService.buscarSolicitacao(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizarSolicitacao(@PathVariable Long id,
			@RequestBody @Valid SolicitacaoAtualizaDTO novosDados) {
		return solicitacaoService.atualizarSolicitacao(id, novosDados);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deletarSolicitacao(@PathVariable Long id) {
		return solicitacaoService.deletarSolicitacao(id);
	}

	@PostMapping("/selecionarCandidatura/aceitar")
	public ResponseEntity<String> selecionarCandidatura(@RequestParam("solicitacaoId") Long solicitacaoId,
			@RequestParam("interpreteId") Long interpreteId) {
		return solicitacaoService.selecionarCandidatura(solicitacaoId, interpreteId);
	}

//	@PutMapping("aceitar/{id}")
//	public ResponseEntity<String> aceitar(@PathVariable Long id) {
//		return solicitacaoService.alterarStatusSolicitacao(id, StatusSolicitacao.ABERTA, StatusSolicitacao.ACEITA);
//	}

	@PutMapping("AguardandoPagamento/{id}")
	public ResponseEntity<String> AguardandoPagamento(@PathVariable Long id) {
		return solicitacaoService.alterarStatusSolicitacao(id, StatusSolicitacao.ACEITA,
				StatusSolicitacao.AGUARDANDO_PAGAMENTO);
	}

	@PutMapping("Paga/{id}")
	public ResponseEntity<String> paga(@PathVariable Long id) {
		return solicitacaoService.alterarStatusSolicitacao(id, StatusSolicitacao.AGUARDANDO_PAGAMENTO,
				StatusSolicitacao.PAGA);
	}

	@PutMapping("Concluida/{id}")
	public ResponseEntity<String> concluida(@PathVariable Long id) {
		return solicitacaoService.alterarStatusSolicitacao(id, StatusSolicitacao.PAGA, StatusSolicitacao.CONCLUIDA);
	}

	@PutMapping("AguardandoAvaliacao/{id}")
	public ResponseEntity<String> AguardandoAvaliacao(@PathVariable Long id) {
		return solicitacaoService.alterarStatusSolicitacao(id, StatusSolicitacao.CONCLUIDA,
				StatusSolicitacao.AGUARDANDO_AVALIACAO);
	}

	@GetMapping("/ListaAguardandoAvaliacao")
	public ResponseEntity<List<SolicitacaoAvaliacao>> aguardandoAvaliacao() {
		List<SolicitacaoAvaliacao> solicitacoesAvaliacao = solicitacaoService.aguardandoAvaliacao();
		return ResponseEntity.ok(solicitacoesAvaliacao);
	}

	@GetMapping("/ListaAguardandoAvaliacaoPorId")
	public ResponseEntity<SolicitacaoAvaliacao> aguardandoAvaliacaoPorId(
			@RequestParam("solicitacaoId") Long solicitacaoId) {
		SolicitacaoAvaliacao solicitacaoAvaliacao = solicitacaoService.aguardandoAvaliacaoPorId(solicitacaoId);

		if (solicitacaoAvaliacao != null) {
			return ResponseEntity.ok(solicitacaoAvaliacao);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}