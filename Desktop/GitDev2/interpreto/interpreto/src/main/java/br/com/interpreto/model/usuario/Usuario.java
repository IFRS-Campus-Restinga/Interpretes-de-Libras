package br.com.interpreto.model.usuario;

import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String cpf;

    String nome;

    String sobrenome;

    String telefone;

    String email;

    String senha;

    LocalDate dataNascimento;

    Boolean ativo;

    Double nota;

    Long quantidadeEncontros;

    public Usuario() {

    }
    //GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long idUsuario) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Long getQuantidadeEncontros() {
        return quantidadeEncontros;
    }

    public void setQuantidadeEncontros(Long quantidadeEncontros) {
        this.quantidadeEncontros = quantidadeEncontros;
    }
}