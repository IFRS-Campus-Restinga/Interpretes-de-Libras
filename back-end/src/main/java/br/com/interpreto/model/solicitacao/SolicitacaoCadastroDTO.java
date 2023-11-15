package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.endereco.Endereco;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Set;

public record SolicitacaoCadastroDTO(

        int duracaoAtendimento,
        Set<Regiao> regioes,
        Set<Especialidade> especialidades,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        String observacaoSolicitacao,
        Endereco endereco

) {
}
