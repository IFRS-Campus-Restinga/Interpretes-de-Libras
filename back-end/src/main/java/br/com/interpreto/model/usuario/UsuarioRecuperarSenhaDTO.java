package br.com.interpreto.model.usuario;

public record UsuarioRecuperarSenhaDTO(
        String email,
        String cpf
) {
}
