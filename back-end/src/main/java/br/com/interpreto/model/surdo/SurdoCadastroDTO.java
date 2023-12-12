package br.com.interpreto.model.surdo;

import br.com.interpreto.infra.validation.constraints.DataNasc;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record SurdoCadastroDTO(
        @NotBlank(message = "CPF é obrigatório")
        @CPF(message = "CPF inválido")
        String cpf,
        @NotBlank(message = "Nome é obrigatório")
        @Length(max = 20, message = "Número máximo de caracteres excedido")
        String nome,
        @NotBlank(message = "Sobrenome é obrigatório")
        @Length(max = 20, message = "Número máximo de caracteres excedido")
        String sobrenome,
        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "^\\d{9}$", message = "Telefone fora do padrão")
        String telefone,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Senha é obrigatória")
        String senha,
        @NotBlank(message = "Role é obrigatória")
        String role,
        @NotBlank(message = "Data nascimento é obrigatória")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato da data deve ser yyyy-mm-dd")
        @DataNasc
        String dataNascimento
) {
}
