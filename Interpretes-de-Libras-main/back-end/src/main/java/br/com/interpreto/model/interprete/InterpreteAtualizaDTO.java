package br.com.interpreto.model.interprete;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InterpreteAtualizaDTO(
        @NotBlank
        String cpf,
        @NotBlank(message = "O nome não pode estar em branco ou nulo!")
        @Length(min= 0 , max = 20)
        String nome,
        @NotBlank
        String username,//NEW!
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
        Set<Especialidade> especialidades,

        Set<Regiao> regioes,

        Double valorHora
) {
}
