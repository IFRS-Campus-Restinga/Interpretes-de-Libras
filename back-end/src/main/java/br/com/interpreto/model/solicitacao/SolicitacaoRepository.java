package br.com.interpreto.model.solicitacao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.interpreto.model.enums.StatusSolicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
	List<Solicitacao> findByInterpreteId(Long interpreteId);

	// Usado para recuperar as Solicitacoes criadas por um Surdo
	List<Solicitacao> findBySurdoId(Long surdoId);

	List<Solicitacao> findByStatusSolicitacao(StatusSolicitacao aguardandoAvaliacao);

	Optional<Solicitacao> findByIdAndStatusSolicitacao(Long id, StatusSolicitacao aguardandoAvaliacao);

}
