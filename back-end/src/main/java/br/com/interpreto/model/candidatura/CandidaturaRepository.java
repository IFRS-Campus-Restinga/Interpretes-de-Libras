package br.com.interpreto.model.candidatura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.solicitacao.Solicitacao;



public interface CandidaturaRepository  extends JpaRepository<Candidatura, Long> {

	boolean existsBySolicitacaoAndInterprete(Solicitacao solicitacao, Interprete interprete);

}
