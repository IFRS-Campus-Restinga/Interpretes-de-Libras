package br.com.interpreto.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("tradutor")
public class Tradutor {
    String nome;
    String telefone;
    String endereco;
    String especialidade;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Tradutor(Long id, String nome, String telfone, String endereco, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.telefone = telfone;
        this.endereco = endereco;
        this.especialidade = especialidade;
    }

    public Tradutor() {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
