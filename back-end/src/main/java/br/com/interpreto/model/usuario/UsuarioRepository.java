package br.com.interpreto.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
    Usuario findByEmailAndCpf(String email, String cpf);

}
