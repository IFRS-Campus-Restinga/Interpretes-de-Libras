package br.com.interpreto.model.usuario;

import br.com.interpreto.model.surdo.Surdo;

import java.util.Optional;

public record UsuarioDetalhamentoDTO(
        Long id

) {
    public UsuarioDetalhamentoDTO(Usuario usuario) {
        this(usuario.getId());
    }
}
