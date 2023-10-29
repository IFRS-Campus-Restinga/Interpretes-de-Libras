package br.com.interpreto.service;

import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteAtualizaDTO;
import br.com.interpreto.model.interprete.InterpreteDetalhamentoDTO;
import br.com.interpreto.model.surdo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurdoService {
	final private SurdoRepository surdoRepository;

	@Autowired
	public SurdoService(SurdoRepository surdoRepository) {
		this.surdoRepository = surdoRepository;
	}

	@Transactional
	public void cadastrarSurdo(SurdoCadastroDTO dados) {
		surdoRepository.save(new Surdo(dados));
	}

	public ResponseEntity<List<SurdoDetalhamentoDTO>> listarSurdo() {
		List<Surdo> listagem = surdoRepository.findAll();

		List<SurdoDetalhamentoDTO> listagemDTO = new ArrayList<>();
		for (Surdo surdo: listagem){
			listagemDTO.add(new SurdoDetalhamentoDTO(surdo));
		}

		return ResponseEntity.ok(listagemDTO);
	}

	public ResponseEntity buscarSurdo(Long id) {
		Surdo surdo = surdoRepository.getReferenceById(id);

		return ResponseEntity.ok(new SurdoDetalhamentoDTO(surdo));
	}


	@Transactional
	public ResponseEntity atualizarSurdo(Long id, SurdoAtualizaDTO novosDados) {
		Surdo surdo = surdoRepository.getReferenceById(id);
		surdo.surdoAtualizarDTO(novosDados);
		surdoRepository.save(surdo);

		return ResponseEntity.ok(new SurdoDetalhamentoDTO(surdo));
	}
	
	@Transactional
	public ResponseEntity deletarSurdo(Long id) {
	    Surdo surdo = surdoRepository.getReferenceById(id);

	        surdoRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
}
