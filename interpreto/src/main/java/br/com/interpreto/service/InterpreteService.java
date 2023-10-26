package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.interprete.InterpreteAtualizaDTO;
import br.com.interpreto.model.interprete.InterpreteCadastroDTO;
import br.com.interpreto.model.interprete.InterpreteRepository;
import br.com.interpreto.model.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class InterpreteService {
	final private InterpreteRepository interpreteRepository;
	final private AvaliacaoUsuarioService avaliacaoUsuarioService;

	@Autowired
	public InterpreteService(InterpreteRepository interpreteRepository, AvaliacaoUsuarioService avaliacaoUsuarioService) {
		this.interpreteRepository = interpreteRepository;
		this.avaliacaoUsuarioService = avaliacaoUsuarioService;
	}

	@Transactional
	public void cadastrarInterprete(InterpreteCadastroDTO dados) {/*
		//Convertendo dados para o tipo Usuario
		ModelMapper modelMapper = new ModelMapper();
		Usuario usuario = modelMapper.map(dados, Usuario.class);
		// Criando a avaliacao usuario ao cadastrar o usuário
		AvaliacaoUsuario avaliacaoUsuario = new AvaliacaoUsuario();
		avaliacaoUsuario.setUsuario(usuario);
		//Convertendo avaliacaoUsuario para o tipo AvaliacaoUsuarioDTO
		ModelMapper modelMapper1 = new ModelMapper();
		AvaliacaoUsuarioCadastroDTO avaliacaoUsuarioCadastroDTO = modelMapper1.map(avaliacaoUsuario, AvaliacaoUsuarioCadastroDTO.class);

		avaliacaoUsuarioService.cadastrarAvaliacaoUsuario(avaliacaoUsuarioCadastroDTO);*/

		interpreteRepository.save(new Interprete(dados));
	}

	public List<Interprete> listarInterprete() {
		return interpreteRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Interprete> buscarInterprete(Long id) {
		return interpreteRepository.findById(id);
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
