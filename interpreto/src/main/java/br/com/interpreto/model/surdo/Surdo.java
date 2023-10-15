package br.com.interpreto.model.surdo;

import br.com.interpreto.model.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "surdo")
public class Surdo extends Usuario {

    //file docCID;
    public Surdo(SurdoCadastroDTO dados) {
        this.setCpf(dados.cpf()); //dados são do tipo SurdoDTO que possuem GETTER, dados.cpf é como se usar esse GETTER
        this.setNome(dados.nome());
        this.setSobrenome(dados.sobrenome());
        this.setTelefone(dados.telefone());
        this.setEmail(dados.email());
        this.setSenha(dados.senha());
        this.setDataNascimento(dados.dataNascimento());
        this.setAtivo(false);

    }
    //CONSTRUTOR
    public Surdo() {

    }

}