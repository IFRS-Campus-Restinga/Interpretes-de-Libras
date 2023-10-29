package br.com.interpreto.model.documento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Documento {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String nomeArquivo;
	//Relacionamento com avaliacaoUsuario

	public Documento(){

	}

	public Long getId() {
		return id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}


}
