package br.com.interpreto.model.interprete;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Set;

public record InterpreteCadastroDTO(
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
        String senhaConfirmation,
        String dataNascimento,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes,
        Double valorHora
) {
}
