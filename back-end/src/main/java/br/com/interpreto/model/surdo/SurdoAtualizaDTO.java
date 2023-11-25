package br.com.interpreto.model.surdo;

import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SurdoAtualizaDTO(
        String nome,
        String sobrenome,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataNascimento,
        String telefone,
        String senha
) {
}
