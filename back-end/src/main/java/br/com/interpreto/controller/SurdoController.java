package br.com.interpreto.controller;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoAtualizaDTO;
import br.com.interpreto.model.surdo.SurdoCadastroDTO;
import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import br.com.interpreto.service.SurdoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/surdo")
public class SurdoController {
	private final SurdoService surdoService;

	@Autowired
	public SurdoController(SurdoService surdoService) {
		this.surdoService = surdoService;
	}

	@GetMapping
	public ResponseEntity<List<SurdoDetalhamentoDTO>> listarSurdo() {
		return surdoService.listarSurdo();
	}

	@PostMapping
	public ResponseEntity cadastrarSurdo(@RequestBody @Valid SurdoCadastroDTO dados, UriComponentsBuilder uriBuilder) {
		return surdoService.cadastrarSurdo(dados, uriBuilder);

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
}
