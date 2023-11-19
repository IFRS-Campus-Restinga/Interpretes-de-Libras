package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.endereco.Endereco;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.surdo.Surdo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record SolicitacaoDetalhamentoDTO(
        Long solicitacaoId,
        Long surdoId,
        Long interpreteId,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        @JsonFormat(pattern="HH:mm")
        LocalTime horaEncontro,
        StatusSolicitacao statusSolicitacao,
        Endereco endereco,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes
) {
    public SolicitacaoDetalhamentoDTO(Solicitacao solicitacao){
        this(solicitacao.getId(), solicitacao.getSurdo().getId(), solicitacao.getInterprete().getId(), solicitacao.getDataEncontro(),
        solicitacao.getHoraEncontro(), solicitacao.getStatusSolicitacao(), solicitacao.getEndereco(),solicitacao.getEspecialidade(), solicitacao.getRegioes());
    }


}
