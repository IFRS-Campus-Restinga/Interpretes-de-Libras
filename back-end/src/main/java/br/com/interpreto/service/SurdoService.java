package br.com.interpreto.service;

import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoAtualizaDTO;
import br.com.interpreto.model.surdo.SurdoCadastroDTO;
import br.com.interpreto.model.surdo.SurdoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Surdo> listarSurdo() {
		return surdoRepository.findAll();
	}

	public Optional<Surdo> buscarSurdo(Long id) {
		return surdoRepository.findById(id);
	}

	//
	@Transactional
	public void atualizarSurdo(Long id, SurdoAtualizaDTO novosDados) {
		Optional<Surdo> opcionalSurdo = surdoRepository.findById(id);
		if (opcionalSurdo.isPresent()) {
			Surdo surdo = opcionalSurdo.get();
			surdo.surdoAtualizarDTO(novosDados);
			surdoRepository.save(surdo);
		}
	}
	
	@Transactional
	public void deletarSurdo(Long id) {
	    Optional<Surdo> opcionalSurdo = surdoRepository.findById(id);
	    if (opcionalSurdo.isPresent()) {
	        surdoRepository.deleteById(id);
	    }
	}
}
