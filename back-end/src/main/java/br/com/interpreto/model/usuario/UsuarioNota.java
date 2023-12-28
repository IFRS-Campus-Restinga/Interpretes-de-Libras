package br.com.interpreto.model.usuario;

import br.com.interpreto.model.interprete.Interprete;

public record UsuarioNota(Long id, String nome, String sobrenome, Double nota) {
	
	public UsuarioNota(Usuario usuario) {
		this(
				usuario.getId(),
				usuario.getNome(),
				usuario.getSobrenome(),
				usuario.getNota()
		);
	}
}
