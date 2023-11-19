package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.endereco.Endereco;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.surdo.Surdo;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record SolicitacaoCadastroDTO(

        int duracaoAtendimento,
        Surdo surdo,
        Interprete interprete,
        Set<Regiao> regioes,
        Set<Especialidade> especialidades,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataEncontro,
        @JsonFormat(pattern="HH:mm")
        LocalTime horaEncontro,
        String observacaoSolicitacao,
        Endereco endereco

) {
}
