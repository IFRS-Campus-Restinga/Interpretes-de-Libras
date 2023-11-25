package br.com.interpreto.model.usuario;

public record LoginRespostaDTO(
        String token,
        String tipoUsuario,
        String id
) {
}
