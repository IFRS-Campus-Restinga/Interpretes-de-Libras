package br.com.interpreto.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioRecuperarSenhaDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @CPF
        String cpf
) {
}
