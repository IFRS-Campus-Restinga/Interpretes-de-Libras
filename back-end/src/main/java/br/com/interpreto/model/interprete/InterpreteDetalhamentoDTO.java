package br.com.interpreto.model.interprete;

import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record InterpreteDetalhamentoDTO(
        Long id,
        String cpf,
        String nome,
        String sobrenome,
        String telefone,
        String email,
        String senha,
        TipoUsuario role,
        @JsonFormat(pattern="yyyy-MM-dd")
        LocalDate dataNascimento,
        Set<Especialidade> especialidades,
        Set<Regiao> regioes,
        Double valorHora) {
    public InterpreteDetalhamentoDTO(Interprete interprete){
            this(interprete.getId(), interprete.getCpf(), interprete.getNome(), interprete.getSobrenome(), interprete.getTelefone(),
                    interprete.getEmail(), interprete.getSenha(), interprete.getRole(), interprete.getDataNascimento(), interprete.getEspecialidade(),
                    interprete.getRegioes(), interprete.getValorHora());
    }
}
