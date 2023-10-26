package br.com.interpreto.model.avaliacaousuario;


import br.com.interpreto.model.enums.StatusAvaliacao;
import br.com.interpreto.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;


public record AvaliacaoUsuarioCadastroDTO(
        Usuario usuario
        //@NotBlank
        //String msg,
        //@JsonFormat(pattern="yyyy-MM-dd")
        //LocalDate dataCriacao,
        //@JsonFormat(pattern="yyyy-MM-dd")
        //LocalDate dataResposta,

        //StatusAvaliacao statusAvaliacao

) {
}
