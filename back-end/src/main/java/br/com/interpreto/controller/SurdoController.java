package br.com.interpreto.controller;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusAvaliacao;
import br.com.interpreto.model.interprete.InterpreteCandidatura;
import br.com.interpreto.model.interprete.InterpreteDetalhamentoDTO;
import br.com.interpreto.model.surdo.SurdoAtualizaDTO;
import br.com.interpreto.model.surdo.SurdoCadastroDTO;
import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import br.com.interpreto.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/surdo")
@CrossOrigin(origins = "*")
public class SurdoController {
	@Autowired
	private SurdoService surdoService;
	@Autowired
	private AvaliacaoUsuarioService avaliacaoUsuarioService;
	@Autowired
	private InterpreteService interpreteService;
	@Autowired
	private SolicitacaoService solicitacaoService;
	@Autowired
	private CandidaturaService candidaturaService;

	@GetMapping
	public ResponseEntity<List<SurdoDetalhamentoDTO>> listarSurdo() {
		return surdoService.listarSurdo();
	}

	@PostMapping
	public ResponseEntity cadastrarSurdo(@RequestParam("dados") String dados,
			@RequestParam("arquivo") MultipartFile arquivo, UriComponentsBuilder uriBuilder)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SurdoCadastroDTO modelDTO = mapper.readValue(dados, SurdoCadastroDTO.class);
		return surdoService.cadastrarSurdo(modelDTO, arquivo, uriBuilder);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarSurdo(@PathVariable Long id) {
		return surdoService.buscarSurdo(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizarSurdo(@PathVariable Long id, @RequestBody @Valid SurdoAtualizaDTO novosDados) {
		return surdoService.atualizarSurdo(id, novosDados);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deletarSurdo(@PathVariable Long id) {
		return surdoService.deletarSurdo(id);
	}

	@GetMapping("/ResultadoSolicitacaoCadastro/{id}")
	public ResponseEntity<String> ResultadoSolicitacaoCadastro(@PathVariable Long id) {
		Optional<AvaliacaoUsuario> resultado = avaliacaoUsuarioService.ReceberResultadoSolicitacaoCadastro(id);

		if (resultado.isPresent()) {
			StatusAvaliacao status = resultado.get().getStatusAvaliacao();

			if (status == StatusAvaliacao.DEFERIDO) {
				return ResponseEntity.ok("Solicitação DEFERIDA");

			} else if (status == StatusAvaliacao.INDEFERIDO) {
				return ResponseEntity.ok("Solicitação INDEFERIDA");

			} else {
				return ResponseEntity.ok("Solicitação EM ANÁLISE");
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Surdo Visualizar Candidaturas de Interpretes na regiao escolhida.
	@GetMapping("ListarCandidaturasInterprete")
	public ResponseEntity<List<InterpreteCandidatura>> ListarCandidaturasInterprete(
			@RequestParam("solicitacaoId") Long solicitacaoId) {
		return solicitacaoService.buscarSolicitacaoSurdo(solicitacaoId).map(solicitacao -> {
			Set<Regiao> regioesSurdo = solicitacao.getRegioes();
			List<InterpreteCandidatura> candidaturas = interpreteService.ListarCandidaturasInterprete().stream()
					.filter(interpreteCandidatura -> interpreteCandidatura.regioes().containsAll(regioesSurdo))
					.collect(Collectors.toList());

			return ResponseEntity.ok(candidaturas);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Surdo selecionar intérprete e atualizar status da solicitação: aguardando_aceite
	@PostMapping("/selecionarCandidaturaInterprete")
	public ResponseEntity<String> selecionarCandidaturaInterprete(@RequestParam("solicitacaoId") Long solicitacaoId,
			@RequestParam("interpreteId") Long interpreteId) {
		return solicitacaoService.selecionarCandidaturaInterprete(solicitacaoId, interpreteId);
	}

	//Listar candidaturas de interprete para solicitações públicas feitas pelo Surdo
	@GetMapping("listarCandidaturasInterprete/{solicitacaoId}")
	public ResponseEntity<List<InterpreteDetalhamentoDTO>> listarCandidaturasInterprete(@PathVariable("solicitacaoId") Long solicitacaoId){
		return candidaturaService.listarCandidaturasInterprete(solicitacaoId);
	}
	//Método para listar as Solicitacoes criadas por determinado Surdo
	@GetMapping("/MinhasSolicitacoes/{surdoId}")
	//Ideia é usar o id que está armazenado no front e passa como parametro
	public ResponseEntity buscarSolicitacoes(@PathVariable("surdoId") Long id) {
		return surdoService.buscarMinhasSolicitacoes(id);
	}
	
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarSolicitacao(@PathVariable Long id) {
        return solicitacaoService.cancelarSolicitacao(id);
    }
}
