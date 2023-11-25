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
        String nome,
        String sobrenome,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataNascimento,
        String telefone,
        String senha,
        Double valorHora,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes
) {
}
