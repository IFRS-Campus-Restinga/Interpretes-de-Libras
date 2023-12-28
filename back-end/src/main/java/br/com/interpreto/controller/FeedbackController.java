package br.com.interpreto.controller;

import br.com.interpreto.model.feedback.Feedback;
import br.com.interpreto.model.feedback.FeedbackLista;
import br.com.interpreto.model.usuario.UsuarioNota;
import br.com.interpreto.service.FeedbackService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/criarFeedback")
	public ResponseEntity<String> criarFeedback(@RequestBody Map<String, Object> requestBody) {
		Long avaliador = Long.valueOf(requestBody.get("avaliador").toString());
		Long avaliado = Long.valueOf(requestBody.get("avaliado").toString());
		Double nota = Double.valueOf(requestBody.get("nota").toString());
		String resenha = (String) requestBody.get("resenha");

		return feedbackService.criarFeedback(avaliador, avaliado, nota, resenha);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Feedback> buscarFeedback(@PathVariable Long id) {
		Optional<Feedback> optionalFeedback = feedbackService.buscarFeedback(id);

		return optionalFeedback.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/avaliador/{id}")
	public ResponseEntity<List<FeedbackLista>> feedbacksDoAvaliador(@PathVariable Long id) {
		List<FeedbackLista> listaAvaliador = feedbackService.feedbacksDoAvaliador(id);
		return ResponseEntity.ok(listaAvaliador);
	}

	@GetMapping("/avaliado/{id}")
	public ResponseEntity<List<FeedbackLista>> feedbacksDoAvaliado(@PathVariable Long id) {
		List<FeedbackLista> listaAvaliado = feedbackService.feedbacksDoAvaliado(id);
		return ResponseEntity.ok(listaAvaliado);
	}

	@GetMapping("findByIdFeedback/{id}")
	public ResponseEntity<FeedbackLista> findByIdFeedback(@PathVariable Long id) {
		Optional<FeedbackLista> feedback = feedbackService.findByIdFeedback(id);

		return feedback.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/findAllFeedbacks")
	public ResponseEntity<List<FeedbackLista>> findAll() {
		List<FeedbackLista> findAllFeedbacks = feedbackService.findAllFeedbacks();
		return ResponseEntity.ok(findAllFeedbacks);
	}
}
