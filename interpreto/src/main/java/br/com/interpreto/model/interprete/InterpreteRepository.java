package br.com.interpreto.model.interprete;

import br.com.interpreto.model.surdo.Surdo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterpreteRepository extends JpaRepository<Interprete, Long> {

}
