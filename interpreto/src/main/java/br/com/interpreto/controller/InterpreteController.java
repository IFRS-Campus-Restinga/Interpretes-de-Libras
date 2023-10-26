package br.com.interpreto.controller;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteAtualizaDTO;
import br.com.interpreto.model.interprete.InterpreteCadastroDTO;
import br.com.interpreto.service.InterpreteService;
import br.com.interpreto.service.AvaliacaoUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interprete")
public class InterpreteController {
	private final InterpreteService interpreteService;
	private final AvaliacaoUsuarioService avaliacaoUsuarioService;

	@Autowired
	public InterpreteController(InterpreteService interpreteService, AvaliacaoUsuarioService avaliacaoUsuarioService) {
		this.interpreteService = interpreteService;
		this.avaliacaoUsuarioService = avaliacaoUsuarioService;
	}
	@PostMapping
	public void cadastrarInterprete(@RequestBody @Valid InterpreteCadastroDTO dados) {
		interpreteService.cadastrarInterprete(dados);

	}
	@GetMapping
	public List<Interprete> listarInterprete() {
		return interpreteService.listarInterprete();
	}
	@GetMapping("/{id}")
	public Optional<Interprete> buscarInterprete(@PathVariable Long id) {
		return interpreteService.buscarInterprete(id);
	}
	@PutMapping("/{id}")
	public void atualizarInterprete(@PathVariable Long id, @RequestBody @Valid InterpreteAtualizaDTO novosDados) {
		interpreteService.atualizarInterprete(id, novosDados);
	}
	@DeleteMapping("/{id}")
	public void deletarInterprete(@PathVariable Long id) {
		interpreteService.deletarInterprete(id);
	}
	//

}
