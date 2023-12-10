package br.com.interpreto.service;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioCadastroDTO;
import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuarioRepository;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.interprete.*;
import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.solicitacao.SolicitacaoDetalhamentoDTO;
import br.com.interpreto.model.surdo.Surdo;
import br.com.interpreto.model.surdo.SurdoDetalhamentoDTO;
import br.com.interpreto.model.usuario.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InterpreteService {
	@Autowired
	private InterpreteRepository interpreteRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;
	@Autowired
	private DocumentoService documentoService;

	@Transactional
	public ResponseEntity cadastrarInterprete(String dados, MultipartFile arquivo, UriComponentsBuilder uriBuilder) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		InterpreteCadastroDTO modelDTO = mapper.readValue(dados, InterpreteCadastroDTO.class);

		Interprete interprete = new Interprete(modelDTO);
		//Verifica se email já existe, caso exista não cria o novo usuário!
		if(this.usuarioRepository.findByEmail(interprete.getEmail()) != null){
			return ResponseEntity.badRequest().body("Email já cadastrado no sistema!");
		}
		//Encriptando a senha do usuário
		String senhaEncriptada = new BCryptPasswordEncoder().encode(interprete.getSenha());
		interprete.setSenha(senhaEncriptada);
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

		//Encriptando a senha do usuário
		String senhaEncriptada = new BCryptPasswordEncoder().encode(novosDados.senha());

		interprete.interpreteAtualizarDTO(novosDados);
		interprete.setSenha(senhaEncriptada);
		interpreteRepository.save(interprete);

		return ResponseEntity.ok(new InterpreteDetalhamentoDTO(interprete));
	}
	@Transactional
	public ResponseEntity deletarInterprete(Long id) {
		Interprete interprete = interpreteRepository.getReferenceById(id);

			interpreteRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	// Surdo Visualizar Candidaturas de Interpretes na regiao escolhida.
	public List<InterpreteCandidatura> ListarCandidaturasInterprete() {
		List<Interprete> listagem = interpreteRepository.findAll();

		return listagem.stream().map(InterpreteCandidatura::new).collect(Collectors.toList());
	}
	// Metodo usado para listar Interpretes com Especialidades/Regioes que a Solicitacao possui, é usado para o Surdo escolher um Interprete
	// ao criar a Solicitacao

	public List<InterpreteDetalhamentoDTO> listarInterpretesSolicitacao (Set<Regiao> regioes, Set<Especialidade> especialidades){
		List<Interprete> listagem = interpreteRepository.findByRegioesAndEspecialidades(regioes, especialidades);

		List<InterpreteDetalhamentoDTO> listagemDTO = new ArrayList<>();
		for (Interprete interprete: listagem){
			listagemDTO.add(new InterpreteDetalhamentoDTO(interprete));
		}
		return listagemDTO;
	}
}
