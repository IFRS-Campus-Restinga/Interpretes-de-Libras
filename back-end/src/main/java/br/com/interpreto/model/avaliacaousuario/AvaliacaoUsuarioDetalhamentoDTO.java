package br.com.interpreto.model.avaliacaousuario;

import br.com.interpreto.model.enums.StatusAvaliacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record AvaliacaoUsuarioDetalhamentoDTO(
        Long id,
        String msg,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataCriacao,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataResposta,

        StatusAvaliacao statusAvaliacao
) {
    public AvaliacaoUsuarioDetalhamentoDTO(AvaliacaoUsuario avaliacaoUsuario){
        this(avaliacaoUsuario.getId(), avaliacaoUsuario.getMsg(), avaliacaoUsuario.getDataCriacao(), avaliacaoUsuario.getDataResposta(),
                avaliacaoUsuario.getStatusAvaliacao());
    }
}
