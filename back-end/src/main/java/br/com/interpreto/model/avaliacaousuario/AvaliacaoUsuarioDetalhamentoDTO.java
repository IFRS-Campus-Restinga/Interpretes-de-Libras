package br.com.interpreto.model.avaliacaousuario;

import br.com.interpreto.model.enums.StatusAvaliacao;
import br.com.interpreto.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record AvaliacaoUsuarioDetalhamentoDTO(
        Long id,
        String msg,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataCriacao,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataResposta,
        StatusAvaliacao statusAvaliacao,
        String nomeUsuario,
        String telefoneUsuario,
        String emailUsuario,
        String tipoUsuario
) {
    public AvaliacaoUsuarioDetalhamentoDTO(AvaliacaoUsuario avaliacaoUsuario){
        this(avaliacaoUsuario.getId(), avaliacaoUsuario.getMsg(), avaliacaoUsuario.getDataCriacao(), avaliacaoUsuario.getDataResposta(),
                avaliacaoUsuario.getStatusAvaliacao(), avaliacaoUsuario.getUsuario().getNome(), avaliacaoUsuario.getUsuario().getTelefone(),
                    avaliacaoUsuario.getUsuario().getEmail(), avaliacaoUsuario.getUsuario().getRole().toString());
    }
}
