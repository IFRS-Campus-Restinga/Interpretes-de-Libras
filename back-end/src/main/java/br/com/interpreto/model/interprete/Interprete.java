package br.com.interpreto.model.interprete;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "interprete")
public class Interprete extends Usuario {

	private Double valorHora;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Especialidade> especialidade;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Regiao> regioes;

	//@Transient //Não cria a coluna no MYSQL
	//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Interprete(@Valid InterpreteCadastroDTO dados) {
		this.setCpf(dados.cpf());
		this.setNome(dados.nome());
		this.setSobrenome(dados.sobrenome());
		this.setTelefone(dados.telefone());
		this.setEmail(dados.email());
		this.setSenha(dados.senha());
		// Converte a data de nascimento da String para LocalDate
		if (dados.dataNascimento() != null) {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dataNascimento = LocalDate.parse(dados.dataNascimento(), inputFormatter);
			this.setDataNascimento(dataNascimento);
		} else {
			this.setDataNascimento(null); // Define como null se a data de nascimento for nula
		}

		//this.setDataNascimento(dados.dataNascimento());
		this.setAtivo(false);
		this.valorHora = dados.valorHora();
		this.especialidade = dados.especialidades();
		this.regioes = dados.regioes();
	}
	
	public void interpreteAtualizarDTO(InterpreteAtualizaDTO novosDados) {
		this.setCpf(novosDados.cpf());
		this.setNome(novosDados.nome());
		this.setSobrenome(novosDados.sobrenome());
		this.setTelefone(novosDados.telefone());
		this.setEmail(novosDados.email());
		this.setSenha(novosDados.senha());
		this.setDataNascimento(novosDados.dataNascimento());
		this.valorHora = novosDados.valorHora();
		this.especialidade = novosDados.especialidades();
		this.regioes = novosDados.regioes();
	}

	// CONSTRUTOR
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
