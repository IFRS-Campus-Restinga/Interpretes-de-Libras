package br.com.interpreto.model.surdo;

import br.com.interpreto.model.avaliacaousuario.AvaliacaoUsuario;
import br.com.interpreto.model.enums.TipoUsuario;
import br.com.interpreto.model.solicitacao.Solicitacao;
import br.com.interpreto.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@DiscriminatorValue("surdo")
public class Surdo extends Usuario {
    @OneToMany(mappedBy = "surdo", fetch = FetchType.EAGER)
    private List<Solicitacao> solicitacao;

    //CONSTRUTOR
    public Surdo() {

    }
    public Surdo(SurdoCadastroDTO dados) {
        this.setCpf(dados.cpf()); //dados são do tipo SurdoDTO que possuem GETTER, dados.cpf é como se usar esse GETTER
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
        this.setRole(TipoUsuario.valueOf(dados.role()));
        this.setAtivo(false);

    }
	public void surdoAtualizarDTO(SurdoAtualizaDTO novosDados) {
		this.setCpf(novosDados.cpf()); //dados são do tipo SurdoDTO que possuem GETTER, dados.cpf é como se usar esse GETTER
        this.setNome(novosDados.nome());
        this.setSobrenome(novosDados.sobrenome());
        this.setTelefone(novosDados.telefone());
        this.setEmail(novosDados.email());
        this.setSenha(novosDados.senha());
        this.setDataNascimento(novosDados.dataNascimento());
        this.setAtivo(false);
	}

}