package br.com.interpreto.model.candidatura;

import br.com.interpreto.model.interprete.Interprete;
import br.com.interpreto.model.solicitacao.Solicitacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidatura")
public class Candidatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sobrenome;
	private Double valorHora;

	@ManyToOne
	@JoinColumn(name = "solicitacao_id")
	private Solicitacao solicitacao;

	@ManyToOne
	@JoinColumn(name = "interprete_id")
	private Interprete interprete;

	public Candidatura() {
		// Default constructor required by JPA
	}

	public Candidatura(Solicitacao solicitacao, Interprete interprete) {
		this.solicitacao = solicitacao;
		this.interprete = interprete;
		this.nome = interprete.getNome();
		this.sobrenome = interprete.getSobrenome();
		this.valorHora = interprete.getValorHora();
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Interprete getInterprete() {
		return interprete;
	}

	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		sobrenome = sobrenome;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

}
