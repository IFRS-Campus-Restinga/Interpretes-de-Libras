package br.com.interpreto.model.interprete;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record InterpreteDTO(
        @NotBlank
        String cpf,
        @NotBlank(message = "O nome não pode estar em branco ou nulo!")
        @Length(min= 0 , max = 20)
        String nome,
        @NotBlank
        String sobrenome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataNascimento,

        Double valorHora
) {
}
