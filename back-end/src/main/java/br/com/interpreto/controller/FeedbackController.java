package br.com.interpreto.controller;

import br.com.interpreto.service.FeedbackService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	private final FeedbackService feedbackService;

	@Autowired
	public FeedbackController(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	@PostMapping("/criarFeedback")
	public ResponseEntity<String> criarFeedback(@RequestBody Map<String, Object> requestBody) {
		Long avaliador = Long.valueOf(requestBody.get("avaliador").toString());
		Long avaliado = Long.valueOf(requestBody.get("avaliado").toString());
		Double nota = Double.valueOf(requestBody.get("nota").toString());
		String resenha = (String) requestBody.get("resenha");

		return feedbackService.criarFeedback(avaliador, avaliado, nota, resenha);
	}
}
