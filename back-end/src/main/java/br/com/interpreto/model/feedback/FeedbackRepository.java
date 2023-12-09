package br.com.interpreto.model.feedback;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	List<Feedback> findAllByAvaliador(Long idAvaliador);
    List<Feedback> findAllByUsuarioId(Long idUsuario);
    Optional<Feedback> findById(Long id);

}
