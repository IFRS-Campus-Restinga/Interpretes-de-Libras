package br.com.interpreto.model.interprete;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.util.List;

@Entity
@Table(name = "interprete")
public class Interprete extends Usuario {

    Double valorHora;
    @Enumerated(EnumType.STRING)
    List<Especialidade> especialidade;
    //file docCertificado;
    @Enumerated(EnumType.STRING)
    List<Regiao> regioes;
    public Interprete(@Valid InterpreteDTO dados) {
        this.setCpf(dados.cpf());
        this.setNome(dados.nome());
        this.setSobrenome(dados.sobrenome());
        this.setTelefone(dados.telefone());
        this.setEmail(dados.email());
        this.setSenha(dados.senha());
        this.setDataNascimento(dados.dataNascimento());
        this.setAtivo(false);
        this.valorHora = dados.valorHora();
    }

}
