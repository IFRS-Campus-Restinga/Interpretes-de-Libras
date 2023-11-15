package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.surdo.Surdo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record SolicitacaoDetalhamentoDTO(
        Long id,
        Surdo surdo,
        Interprete interprete,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        StatusSolicitacao statusSolicitacao
) {
    public SolicitacaoDetalhamentoDTO(Solicitacao solicitacao){
        this(solicitacao.getId(), solicitacao.getSurdo(), solicitacao.getInterprete(), solicitacao.getEspecialidade(), solicitacao.getRegioes(), solicitacao.getDataEncontro(),
        solicitacao.getStatusSolicitacao());
    }
}
