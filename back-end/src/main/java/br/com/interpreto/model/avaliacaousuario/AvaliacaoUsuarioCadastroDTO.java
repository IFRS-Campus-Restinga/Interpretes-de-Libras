package br.com.interpreto.model.avaliacaousuario;


import br.com.interpreto.model.usuario.Usuario;


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
