package br.com.interpreto.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradutorRepository extends JpaRepository<Tradutor, Long> {

}
