package br.com.interpreto.model.interprete;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.TipoUsuario;
import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("interprete")
public class Interprete extends Usuario {

	private Double valorHora;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Especialidade> especialidades;
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private Set<Regiao> regioes;
	@OneToMany(mappedBy = "interprete", fetch = FetchType.EAGER)
	private List<Solicitacao> solicitacao;

	public Interprete(@Valid InterpreteCadastroDTO dados) {
		this.setCpf(dados.cpf());
		this.setNome(dados.nome());
		this.setSobrenome(dados.sobrenome());
		this.setTelefone(dados.telefone());
		this.setEmail(dados.email());
		this.setSenha(dados.senha());
		// Converte a data de nascimento da String para LocalDate
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataNascimento = LocalDate.parse(dados.dataNascimento(), inputFormatter);
		this.setDataNascimento(dataNascimento);
		this.setRole(TipoUsuario.valueOf(dados.role()));
		this.setAtivo(false);
		this.valorHora = dados.valorHora();
		this.especialidades = dados.especialidades();
		this.regioes = dados.regioes();
	}
	
	public void interpreteAtualizarDTO(InterpreteAtualizaDTO novosDados) {
		this.setNome(novosDados.nome());
		this.setSobrenome(novosDados.sobrenome());
		// Converte a data de nascimento da String para LocalDate
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataNascimento = LocalDate.parse(novosDados.dataNascimento(), inputFormatter);
		this.setDataNascimento(dataNascimento);
		this.setTelefone(novosDados.telefone());
		this.setSenha(novosDados.senha());
		this.valorHora = novosDados.valorHora();
		this.especialidades = novosDados.especialidades();
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
		return especialidades;
	}

	public void setEspecialidade(Set<Especialidade> especialidade) {
		this.especialidades = especialidade;
	}

	public Set<Regiao> getRegioes() {
		return regioes;
	}

	public void setRegioes(Set<Regiao> regioes) {
		this.regioes = regioes;
	}
}
