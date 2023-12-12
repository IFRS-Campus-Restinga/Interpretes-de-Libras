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
    //Usado para o Surdo escolher um Interprete ao criar uma Solicitacao
    //List<Interprete> findByRegioesAndEspecialidades(Set<Regiao> regioes, Set<Especialidade> especialidades);
    //@Query("SELECT i FROM Interprete i WHERE i.regioes IN :regioes AND i.especialidades IN :especialidades")
    //List<Interprete> findByRegioesAndEspecialidades(@Param("regioes") Set<Regiao> regioes, @Param("especialidades") Set<Especialidade> especialidades);
}
