package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.endereco.Endereco;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.surdo.Surdo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record SolicitacaoCadastroDTO(
        @Positive
        @DecimalMax(value = "9")
        int duracaoAtendimento,
        @NotNull
        Surdo surdo,
        Interprete interprete,
        Set<Regiao> regioes,
        Set<Especialidade> especialidades,
        @NotNull
        @Future
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        @NotNull
        @JsonFormat(pattern="HH:mm")
        LocalTime horaEncontro,
        @Size(max = 254)
        String observacaoSolicitacao,
        @NotNull
        Endereco endereco

) {
}
