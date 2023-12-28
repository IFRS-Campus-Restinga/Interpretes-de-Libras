package br.com.interpreto.model.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioTrocarSenhaDTO(
        @NotNull
        Long id,
        @NotBlank
        String senha
) {
}
