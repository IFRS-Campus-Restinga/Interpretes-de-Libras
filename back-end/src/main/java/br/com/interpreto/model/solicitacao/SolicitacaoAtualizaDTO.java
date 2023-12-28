package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.endereco.Endereco;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.interprete.Interprete;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record SolicitacaoAtualizaDTO(
        Interprete interprete,
        Set<Regiao> regioes,
        Set<Especialidade> especialidade,
        @Future
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        @JsonFormat(pattern="HH:mm")
        LocalTime horaEncontro,
        @Positive
        @DecimalMax(value = "9")
        int duracaoAtendimento,
        StatusSolicitacao statusSolicitacao,
        @Size(max = 254)
        String observacaoSolicitacao,
        @Positive(message = "Nota Surdo precisa ser maior que 0")
        Double notaSurdo,
        @Positive(message = "Nota Surdo precisa ser maior que 0")
        Double notaInterprete,
        Endereco endereco
) {
}
