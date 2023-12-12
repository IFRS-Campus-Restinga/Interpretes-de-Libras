package br.com.interpreto.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha
) {
}
