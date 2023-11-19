package br.com.interpreto.model.interprete;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.solicitacao.*;


public record InterpreteCandidatura(
        Long id,
        String nome,
        String sobrenome,
        String telefone,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes,
        Double valorHora) {
	public InterpreteCandidatura(Interprete interprete) {
		this(
				interprete.getId(),
				interprete.getNome(),
				interprete.getSobrenome(),
				interprete.getTelefone(),
				interprete.getEspecialidade(),
				interprete.getRegioes(),
				interprete.getValorHora()
		);
	}
}
