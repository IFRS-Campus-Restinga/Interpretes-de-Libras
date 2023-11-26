package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioRepository;
import br.com.interpreto.model.surdo.*;
import br.com.interpreto.model.usuario.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurdoService {
	final private SurdoRepository surdoRepository;
	final private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;
	final private DocumentoService documentoService;
	final private UsuarioRepository usuarioRepository;

	@Autowired //INJECAO DE DEPENDENCIA VIA CONSTRUTOR
	public SurdoService(SurdoRepository surdoRepository, AvaliacaoUsuarioRepository avaliacaoUsuarioRepository,
						DocumentoService documentoService, UsuarioRepository usuarioRepository) {
		this.surdoRepository = surdoRepository;
		this.avaliacaoUsuarioRepository = avaliacaoUsuarioRepository;
		this.documentoService = documentoService;
		this.usuarioRepository = usuarioRepository;
	}
	@Transactional
	public ResponseEntity cadastrarSurdo(String dados, MultipartFile arquivo, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SurdoCadastroDTO modelDTO = mapper.readValue(dados, SurdoCadastroDTO.class);

		Surdo surdo = new Surdo(modelDTO);
		//Verifica se email já existe, caso exista não cria o novo usuário!
		if(this.usuarioRepository.findByEmail(surdo.getEmail()) != null){
			return ResponseEntity.badRequest().body("Email já cadastrado no sistema!");
		}
		//Encriptando a senha do usuário
		String senhaEncriptada = new BCryptPasswordEncoder().encode(surdo.getSenha());
		surdo.setSenha(senhaEncriptada);
		surdoRepository.save(surdo);

		//Na parte abaixo ocorre o salvamento da Avaliacao e a vinculacao do documento na avaliacao
		AvaliacaoUsuarioCadastroDTO avaliacaoDTO = new AvaliacaoUsuarioCadastroDTO(surdo);
		AvaliacaoUsuario avaliacao = new AvaliacaoUsuario(avaliacaoDTO);
		avaliacaoUsuarioRepository.save(avaliacao);
		documentoService.salvarDocumento(arquivo, avaliacao);

		var uri = uriBuilder.path("/surdo/{id}").buildAndExpand(surdo.getId()).toUri();

		return ResponseEntity.created(uri).body(new SurdoDetalhamentoDTO(surdo));
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

		//Encriptando a senha do usuário
		String senhaEncriptada = new BCryptPasswordEncoder().encode(novosDados.senha());

		surdo.surdoAtualizarDTO(novosDados);
		surdo.setSenha(senhaEncriptada);
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
