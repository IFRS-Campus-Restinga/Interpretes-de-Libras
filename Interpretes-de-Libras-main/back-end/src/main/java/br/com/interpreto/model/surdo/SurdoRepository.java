package br.com.interpreto.model.surdo;

import br.com.interpreto.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurdoRepository extends JpaRepository<Surdo, Long> {

}
