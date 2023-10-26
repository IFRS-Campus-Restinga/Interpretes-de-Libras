package br.com.interpreto.model.interprete;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.surdo.SurdoAtualizaDTO;
import br.com.interpreto.model.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.Set;

@Entity
@Table(name = "interprete")
public class Interprete extends Usuario {

	private Double valorHora;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Especialidade> especialidade;
	// file docCertificado;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Regiao> regioes;

	public Interprete(@Valid InterpreteCadastroDTO dados) {
		this.setCpf(dados.cpf());
		this.setNome(dados.nome());
		this.setUsername(dados.username());//NEW!
		this.setSobrenome(dados.sobrenome());
		this.setTelefone(dados.telefone());
		this.setEmail(dados.email());
		this.setSenha(dados.senha());
		this.setDataNascimento(dados.dataNascimento());
		this.setAtivo(false);
		this.valorHora = dados.valorHora();
		this.especialidade = dados.especialidades();
		this.regioes = dados.regioes();
	}
	
	public void interpreteAtualizarDTO(@Valid InterpreteAtualizaDTO novosDados) {
		this.setCpf(novosDados.cpf());
		this.setNome(novosDados.nome());
		this.setUsername(novosDados.username());//NEW!
		this.setSobrenome(novosDados.sobrenome());
		this.setTelefone(novosDados.telefone());
		this.setEmail(novosDados.email());
		this.setSenha(novosDados.senha());
		this.setDataNascimento(novosDados.dataNascimento());
		this.setAtivo(false);
		this.valorHora = novosDados.valorHora();
		this.especialidade = novosDados.especialidades();
		this.regioes = novosDados.regioes();
	}

	// CONSTRUTOR
	@Deprecated //Funcionamento do Hibernate
	public Interprete() {
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public Set<Especialidade> getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Set<Especialidade> especialidade) {
		this.especialidade = especialidade;
	}

	public Set<Regiao> getRegioes() {
		return regioes;
	}

	public void setRegioes(Set<Regiao> regioes) {
		this.regioes = regioes;
	}
}
