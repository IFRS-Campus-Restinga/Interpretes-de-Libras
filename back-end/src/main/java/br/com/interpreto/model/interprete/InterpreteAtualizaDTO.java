package br.com.interpreto.model.interprete;

import java.time.LocalDate;
import java.util.Set;

import br.com.interpreto.infra.validation.constraints.DataNasc;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InterpreteAtualizaDTO(
        @Length(max = 20, message = "Número máximo de caracteres excedido")
        String nome,
        @Length(max = 20, message = "Número máximo de caracteres excedido")
        String sobrenome,
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato da data deve ser yyyy-mm-dd")
        @DataNasc
        String dataNascimento,
        @Pattern(regexp = "^\\d{9}$", message = "Telefone fora do padrão")
        String telefone,
        String senha,
        @Positive(message = "Valor Hora precisa ser maior que 0")
        Double valorHora,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes
) {
}
