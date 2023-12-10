package br.com.interpreto.service;

import br.com.interpreto.model.feedback.Feedback;
import br.com.interpreto.model.feedback.FeedbackLista;
import br.com.interpreto.model.feedback.FeedbackRepository;
import br.com.interpreto.model.usuario.Usuario;
import br.com.interpreto.model.usuario.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public ResponseEntity<String> criarFeedback(Long avaliador, Long avaliado, Double nota, String resenha) {

		Optional<Usuario> optionalUsuario = usuarioRepository.findById(avaliado);

		if (optionalUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}

		Usuario usuario = optionalUsuario.get();

		Feedback feedback = new Feedback(usuario);
		feedback.setResenha(resenha);
		feedback.setNota(nota);
		feedback.setIdAvaliador(avaliador);

		feedbackRepository.save(feedback);

		usuario.setNota(usuario.calcularMediaNotas());
		usuarioRepository.save(usuario);

		return ResponseEntity.status(HttpStatus.CREATED).body("Feedback criado com sucesso");
	}
	
	public Optional<Feedback> buscarFeedback(Long id) {
		return feedbackRepository.findById(id);
	}

	public List<FeedbackLista> feedbacksDoAvaliador(Long idAvaliador) {
		List<Feedback> feedbacks = feedbackRepository.findAllByAvaliador(idAvaliador);
		return feedbacks.stream().map(this::mapToFeedbackLista).collect(Collectors.toList());
	}

	public List<FeedbackLista> feedbacksDoAvaliado(Long idAvaliado) {
		List<Feedback> feedbacks = feedbackRepository.findAllByUsuarioId(idAvaliado);
		return feedbacks.stream().map(this::mapToFeedbackLista).collect(Collectors.toList());
	}

	public Optional<FeedbackLista> findByIdFeedback(Long id) {
		Optional<Feedback> feedback = feedbackRepository.findById(id);
		return feedback.map(this::mapToFeedbackLista);
	}

	public List<FeedbackLista> findAllFeedbacks() {
		List<Feedback> feedbacks = feedbackRepository.findAll();
		return feedbacks.stream().map(this::mapToFeedbackLista).collect(Collectors.toList());
	}

	private FeedbackLista mapToFeedbackLista(Feedback feedback) {
		String nomeAvaliado = (feedback.getUsuario() != null) ? feedback.getUsuario().getNome() : null;
		String nomeAvaliador = obterNomeAvaliador(feedback.getIdAvaliador());

		return new FeedbackLista(feedback.getId(), feedback.getIdAvaliador(), feedback.getUsuario().getId(),
				nomeAvaliador, nomeAvaliado, feedback.getNota(), feedback.getResenha());
	}

	private String obterNomeAvaliador(Long idAvaliador) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idAvaliador);
		return usuarioOptional.map(Usuario::getNome).orElse(null);
	}
}
