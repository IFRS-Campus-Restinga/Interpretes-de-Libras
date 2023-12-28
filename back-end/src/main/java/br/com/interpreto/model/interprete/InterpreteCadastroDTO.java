package br.com.interpreto.model.interprete;

import br.com.interpreto.infra.validation.constraints.DataNasc;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

public record InterpreteCadastroDTO(@NotBlank(message = "CPF é obrigatório") @CPF(message = "CPF inválido") String cpf,
                @NotBlank(message = "Nome é obrigatório") @Length(max = 20, message = "Número máximo de caracteres excedido") String nome,
                @NotBlank(message = "Sobrenome é obrigatório") @Length(max = 20, message = "Número máximo de caracteres excedido") String sobrenome,
                @NotBlank(message = "Telefone é obrigatório") @Pattern(regexp = "^\\d{9}$", message = "Telefone fora do padrão") String telefone,
                @NotBlank(message = "Email é obrigatório") @Email(message = "Email inválido") String email,
                @NotBlank(message = "Senha é obrigatória") String senha, String senhaConfirmation,
                @NotBlank(message = "Role é obrigatória") String role,
                @NotBlank(message = "Data nascimento é obrigatória") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato da data deve ser yyyy-mm-dd") @DataNasc String dataNascimento,
                Set<Especialidade> especialidades,
                Set<Regiao> regioes,
                int idSurdo,
                @NotNull @Positive(message = "Valor Hora precisa ser maior que 0") Double valorHora) {
}
