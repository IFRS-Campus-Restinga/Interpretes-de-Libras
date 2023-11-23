package br.com.interpreto.model.solicitacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
	List<Solicitacao> findByInterpreteId(Long interpreteId);
}
