package br.com.interpreto.controller;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.enums.StatusAvaliacao;
import br.com.interpreto.model.surdo.SurdoAtualizaDTO;
import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import br.com.interpreto.service.AvaliacaoUsuarioService;
import br.com.interpreto.service.SurdoService;
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
@RequestMapping("/surdo")
@CrossOrigin(origins = "*")
public class SurdoController {
	private final SurdoService surdoService;
	private final AvaliacaoUsuarioService avaliacaoUsuarioService;

	@Autowired // INJECAO DE DEPENDENCIA VIA CONSTRUTOR
	public SurdoController(SurdoService surdoService, AvaliacaoUsuarioService avaliacaoUsuarioService) {
		this.surdoService = surdoService;
		this.avaliacaoUsuarioService = avaliacaoUsuarioService;
	}

	@GetMapping
	public ResponseEntity<List<SurdoDetalhamentoDTO>> listarSurdo() {
		return surdoService.listarSurdo();
	}

	@PostMapping
	public ResponseEntity cadastrarSurdo(@RequestParam("dados") String dados,
			@RequestParam("arquivo") MultipartFile arquivo, UriComponentsBuilder uriBuilder)
			throws JsonProcessingException {
		return surdoService.cadastrarSurdo(dados, arquivo, uriBuilder);
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
}
