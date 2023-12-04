package br.com.interpreto.service;

import br.com.interpreto.model.feedback.Feedback;
import br.com.interpreto.model.feedback.FeedbackRepository;
import br.com.interpreto.model.usuario.Usuario;
import br.com.interpreto.model.usuario.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

	private final FeedbackRepository feedbackRepository;
	private final UsuarioRepository usuarioRepository;

	@Autowired
	public FeedbackService(FeedbackRepository feedbackRepository, UsuarioRepository usuarioRepository) {
		this.feedbackRepository = feedbackRepository;
		this.usuarioRepository = usuarioRepository;
	}

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
}
