package br.com.interpreto.model.surdo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record SurdoDetalhamentoDTO(
        Long id,
        String cpf,
        String nome,
        String sobrenome,
        String telefone,
        String email,
        String senha,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataNascimento
) {
    public SurdoDetalhamentoDTO(Surdo surdo){
        this(surdo.getId(), surdo.getCpf(), surdo.getNome(), surdo.getSobrenome(), surdo.getTelefone(),
                surdo.getEmail(), surdo.getSenha(), surdo.getDataNascimento());
    }
}
