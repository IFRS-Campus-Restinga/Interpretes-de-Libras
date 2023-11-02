package br.com.interpreto.controller;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoAtualizaDTO;
import br.com.interpreto.model.surdo.SurdoCadastroDTO;
import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import br.com.interpreto.service.SurdoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/surdo")
@CrossOrigin(origins = "*")
public class SurdoController {
	private final SurdoService surdoService;

	@Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
	public SurdoController(SurdoService surdoService) {
		this.surdoService = surdoService;
	}

	@GetMapping
	public ResponseEntity<List<SurdoDetalhamentoDTO>> listarSurdo() {
		return surdoService.listarSurdo();
	}

	@PostMapping
	public ResponseEntity cadastrarSurdo(@RequestParam("dados") String dados, @RequestParam("arquivo") MultipartFile arquivo, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
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
}
