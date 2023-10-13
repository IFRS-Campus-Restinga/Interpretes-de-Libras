package br.com.interpreto.model.surdo;

import br.com.interpreto.model.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "surdo")
//@PrimaryKeyJoinColumn(name="idUsuario")
public class Surdo extends Usuario {

    private String teste;
    //file docCID;
    public Surdo(SurdoDTO dados) {
        this.setCpf(dados.cpf());
        this.setNome(dados.nome());
        this.setSobrenome(dados.sobrenome());
        this.setTelefone(dados.telefone());
        this.setEmail(dados.email());
        this.setSenha(dados.senha());
        this.setDataNascimento(dados.dataNascimento());
        this.setAtivo(false);
        this.teste = "teste";
    }

}