package br.com.interpreto.model.avaliacaousuario;

import br.com.interpreto.model.enums.StatusAvaliacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record AvaliacaoUsuarioAtualizaDTO(
        @NotBlank //usado somente para strings
        String msg,
        @NotNull
        StatusAvaliacao statusAvaliacao
) {
}
