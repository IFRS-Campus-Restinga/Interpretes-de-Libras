package br.com.interpreto.status;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

    List<Entidade> findByStatus(Status status);

    // Outros métodos de consulta conforme necessário
}
