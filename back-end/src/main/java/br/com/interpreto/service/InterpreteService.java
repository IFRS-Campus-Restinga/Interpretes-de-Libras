package br.com.interpreto.service;

import br.com.interpreto.model.interprete.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InterpreteService {
	final private InterpreteRepository interpreteRepository;

	@Autowired
	public InterpreteService(InterpreteRepository interpreteRepository) {
		this.interpreteRepository = interpreteRepository;
	}

	@Transactional
	public void cadastrarInterprete(InterpreteCadastroDTO dados) {
		interpreteRepository.save(new Interprete(dados));
	}

	public ResponseEntity<List<InterpreteDetalhamentoDTO>> listarInterprete() {
		List<Interprete> listagem = interpreteRepository.findAll();

		List<InterpreteDetalhamentoDTO> listagemDTO = new ArrayList<>();
		for (Interprete interprete: listagem){
			listagemDTO.add(new InterpreteDetalhamentoDTO(interprete));
		}

		return ResponseEntity.ok(listagemDTO);
	}

	public ResponseEntity buscarInterprete(Long id) {
		Interprete interprete = interpreteRepository.getReferenceById(id);

		return ResponseEntity.ok(new InterpreteDetalhamentoDTO(interprete));
	}

	//
	@Transactional
	public void atualizarInterprete(Long id, InterpreteAtualizaDTO novosDados) {
		Optional<Interprete> opcionalInterprete = interpreteRepository.findById(id);
		if (opcionalInterprete.isPresent()) {
			Interprete interprete = opcionalInterprete.get();
			interprete.interpreteAtualizarDTO(novosDados);
			interpreteRepository.save(interprete);
		}
	}

	@Transactional
	public void deletarInterprete(Long id) {
		Optional<Interprete> opcionalInterprete = interpreteRepository.findById(id);
		if (opcionalInterprete.isPresent()) {
			interpreteRepository.deleteById(id);
		}
	}
}
