package br.com.interpreto.model.interprete;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface InterpreteRepository extends JpaRepository<Interprete, Long> {
    // Usado para o Surdo escolher um Interprete ao criar uma Solicitacao
    List<Interprete> findByEspecialidadesIn(List<Especialidade> especialidades);

    List<Interprete> findByRegioesIn(List<Regiao> regioes);
}
