package br.com.interpreto.controller;

import br.com.interpreto.model.usuario.UsuarioRecuperarSenhaDTO;
import br.com.interpreto.model.usuario.Usuario;
import br.com.interpreto.model.usuario.UsuarioNota;
import br.com.interpreto.model.usuario.UsuarioTrocarSenhaDTO;
import br.com.interpreto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuario() {
		return usuarioService.listarUsuario();
	}

	@PostMapping("/recuperarsenha")
	public ResponseEntity recuperarSenha(@RequestBody @Valid UsuarioRecuperarSenhaDTO dados) {
		return usuarioService.recuperarSenha(dados);
	}

	@PutMapping("/recuperarsenha")
	public ResponseEntity trocarSenha(@RequestBody @Valid UsuarioTrocarSenhaDTO dados) {
		return usuarioService.trocarSenha(dados);
	}

	@GetMapping("/nota")
	public ResponseEntity<List<UsuarioNota>> FeedbackNotaPorId(@RequestParam("id") Long id) {
		List<UsuarioNota> usuarioNota = usuarioService.FeedbackNotaPorId(id);
		return ResponseEntity.ok(usuarioNota);
	}

	@GetMapping("/{id}/link-whatsapp")
	public ResponseEntity<String> gerarLinkWhatsapp(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);

		if (usuario != null) {
			String linkWhatsapp = usuario.gerarLinkWhatsapp();

			if (linkWhatsapp != null) {
				return ResponseEntity.ok(linkWhatsapp);
			}
		}

		return ResponseEntity.notFound().build();
	}

}
