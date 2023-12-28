package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.enums.StatusSolicitacao;



public record SolicitacaoAvaliacao(

        StatusSolicitacao statusSolicitacao,
        Long surdoId,
        Long interpreteId,
        Long solicitacaoId
) {
    public SolicitacaoAvaliacao(Solicitacao solicitacao) {
        this(

            solicitacao.getStatusSolicitacao(),
            solicitacao.getSurdo().getId(),
            solicitacao.getInterprete().getId()!= null ? solicitacao.getInterprete().getId() : null,
            solicitacao.getId()
        );
    }
}