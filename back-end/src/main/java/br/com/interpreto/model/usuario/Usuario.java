package br.com.interpreto.model.usuario;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;

    @JsonFormat(pattern="yyyy-MM-dd")//Deve ser verificado
    private LocalDate dataNascimento;
    private Boolean ativo;
    private Double nota;
    private Long quantidadeEncontros;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)//Para tornar Bidirecional...
    @JsonManagedReference
    private List<AvaliacaoUsuario> avaliacaoUsuario;
    public Usuario() {

    }
    //GETTERS AND SETTERS
    public Long getId() {
        return id;
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

    public List<AvaliacaoUsuario> getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(List<AvaliacaoUsuario> avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }
}