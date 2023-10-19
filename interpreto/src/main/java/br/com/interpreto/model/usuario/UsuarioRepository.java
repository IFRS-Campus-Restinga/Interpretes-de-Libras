package br.com.interpreto.model.usuario;

import org.springframework.data.repository.CrudRepository;

import br.com.interpreto.model.surdo.Surdo;

public interface UsuarioRepository  extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}

//public interface UsuarioRepository  extends CrudRepository<Surdo, Long> {
//    Surdo findByUsername(String username);
//    Surdo findByEmail(String email);
//}