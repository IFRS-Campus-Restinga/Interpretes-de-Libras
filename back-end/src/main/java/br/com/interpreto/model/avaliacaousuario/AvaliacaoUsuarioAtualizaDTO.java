package br.com.interpreto.model.avaliacaousuario;

import br.com.interpreto.model.enums.StatusAvaliacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoUsuarioAtualizaDTO(
        @NotBlank //usado somente para strings
        String msg,
        @NotNull
        StatusAvaliacao statusAvaliacao
) {
}
