package br.com.interpreto.model.solicitacao;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.interpreto.model.endereco.Endereco;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;

public record SolicitacaoLista(
        int duracaoAtendimento,
        String nomeSurdo,
        Set<Regiao> regioes,
        Set<Especialidade> especialidades,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        String observacaoSolicitacao,
        Endereco endereco,
        StatusSolicitacao statusSolicitacao,
        Long surdoId,
        Long solicitacaoId
) {
    public SolicitacaoLista(Solicitacao solicitacao) {
        this(
            solicitacao.getDuracaoAtendimento(),
            solicitacao.getSurdo().getNome(),
            solicitacao.getRegioes(),
            solicitacao.getEspecialidade(),
            solicitacao.getDataEncontro(),
            solicitacao.getObservacaoSolicitacao(),
            solicitacao.getEndereco(),
            solicitacao.getStatusSolicitacao(),
            solicitacao.getSurdo().getId(),
            solicitacao.getId()
        );
    }
}
