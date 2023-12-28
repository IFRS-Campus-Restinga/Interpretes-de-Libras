package br.com.interpreto.model.surdo;

import java.time.LocalDate;

import br.com.interpreto.infra.validation.constraints.DataNasc;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SurdoAtualizaDTO(
        @Length(max = 20, message = "Número máximo de caracteres excedido")
        String nome,
        @Length(max = 20, message = "Número máximo de caracteres excedido")
        String sobrenome,
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato da data deve ser yyyy-mm-dd")
        @DataNasc
        String dataNascimento,
        @Pattern(regexp = "^\\d{9}$", message = "Telefone fora do padrão")
        String telefone,
        String senha
) {
}
