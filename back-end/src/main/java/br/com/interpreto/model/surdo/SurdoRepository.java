package br.com.interpreto.model.surdo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurdoRepository extends JpaRepository<Surdo, Long> {

}
