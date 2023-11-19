package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.endereco.Endereco;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.interprete.Interprete;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record SolicitacaoAtualizaDTO(
        Interprete interprete,
        Set<Regiao> regioes,
        Set<Especialidade> especialidade,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        int duracaoAtendimento,
        StatusSolicitacao statusSolicitacao,
        String observacaoSolicitacao,
        Double notaSurdo,
        Double notaInterprete,
        Endereco endereco
) {
}
