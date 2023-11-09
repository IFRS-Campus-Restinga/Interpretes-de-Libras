package br.com.interpreto.controller;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.enums.StatusAvaliacao;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteAtualizaDTO;
import br.com.interpreto.model.interprete.InterpreteCadastroDTO;
import br.com.interpreto.model.interprete.InterpreteDetalhamentoDTO;
import br.com.interpreto.service.AvaliacaoUsuarioService;
import br.com.interpreto.service.InterpreteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interprete")
@CrossOrigin(origins = "*")
public class InterpreteController {
	private final InterpreteService interpreteService;
	private AvaliacaoUsuarioService avaliacaoUsuarioService;

	@Autowired // INJECAO DE DEPENDENCIA VIA CONSTRUTOR
	public InterpreteController(InterpreteService interpreteService, AvaliacaoUsuarioService avaliacaoUsuarioService) {
		this.interpreteService = interpreteService;
		this.avaliacaoUsuarioService = avaliacaoUsuarioService;
	}

	@GetMapping
	public ResponseEntity<List<InterpreteDetalhamentoDTO>> listarInterprete() {
		return interpreteService.listarInterprete();
	}

	@PostMapping
	public ResponseEntity cadastrarInterprete(@RequestParam("dados") String dados,
			@RequestParam("arquivo") MultipartFile arquivo, UriComponentsBuilder uriBuilder)
			throws JsonProcessingException {
		return interpreteService.cadastrarInterprete(dados, arquivo, uriBuilder);
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarInterprete(@PathVariable Long id) {
		return interpreteService.buscarInterprete(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizarInterprete(@PathVariable Long id,
			@RequestBody @Valid InterpreteAtualizaDTO novosDados) {
		return interpreteService.atualizarInterprete(id, novosDados);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity deletarInterprete(@PathVariable Long id) {
		return interpreteService.deletarInterprete(id);
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

}
