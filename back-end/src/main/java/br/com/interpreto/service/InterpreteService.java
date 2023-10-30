package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioRepository;
import br.com.interpreto.model.interprete.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterpreteService {
	final private InterpreteRepository interpreteRepository;
	final private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;
	final private DocumentoService documentoService;
	@Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
	public InterpreteService(InterpreteRepository interpreteRepository, AvaliacaoUsuarioRepository avaliacaoUsuarioRepository, DocumentoService documentoService) {
		this.interpreteRepository = interpreteRepository;
		this.avaliacaoUsuarioRepository = avaliacaoUsuarioRepository;
		this.documentoService = documentoService;
	}

	@Transactional
	public ResponseEntity cadastrarInterprete(String dados, MultipartFile arquivo, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		InterpreteCadastroDTO modelDTO = mapper.readValue(dados, InterpreteCadastroDTO.class);

		Interprete interprete = new Interprete(modelDTO);
		interpreteRepository.save(interprete);
		//Na parte abaixo ocorre o salvamento da Avaliacao e a vinculacao do documento na avaliacao
		AvaliacaoUsuarioCadastroDTO avaliacaoDTO = new AvaliacaoUsuarioCadastroDTO(interprete);
		AvaliacaoUsuario avaliacao = new AvaliacaoUsuario(avaliacaoDTO);
		avaliacaoUsuarioRepository.save(avaliacao);
		documentoService.salvarDocumento(arquivo , avaliacao);

		var uri = uriBuilder.path("/interprete/{id}").buildAndExpand(interprete.getId()).toUri();

		return ResponseEntity.created(uri).body(new InterpreteDetalhamentoDTO(interprete));
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


	@Transactional
	public ResponseEntity atualizarInterprete(Long id, InterpreteAtualizaDTO novosDados) {
		Interprete interprete = interpreteRepository.getReferenceById(id);
			interprete.interpreteAtualizarDTO(novosDados);
			interpreteRepository.save(interprete);

		return ResponseEntity.ok(new InterpreteDetalhamentoDTO(interprete));
	}


	@Transactional
	public ResponseEntity deletarInterprete(Long id) {
		Interprete interprete = interpreteRepository.getReferenceById(id);

			interpreteRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
