package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record SolicitacaoDetalhamentoDTO(
        Long id,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        StatusSolicitacao statusSolicitacao
) {
    public SolicitacaoDetalhamentoDTO(Solicitacao solicitacao){
        this(solicitacao.getId(), solicitacao.getEspecialidade(), solicitacao.getRegioes(), solicitacao.getDataEncontro(),
        solicitacao.getStatusSolicitacao());
    }
}
