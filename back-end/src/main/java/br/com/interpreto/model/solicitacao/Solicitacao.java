package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.documento.Documento;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.endereco.Endereco;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Regiao> regioes;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Especialidade> especialidade;
    @Temporal(TemporalType.DATE)
    private LocalDate dataCriacao;
    @Temporal(TemporalType.TIME)
    private LocalTime horaCriacao;
    private int duracaoAtendimento;
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;
    private String observacaoSolicitacao;
    @OneToOne
    @JoinColumn(name = "documento_id")
    private Documento comprovantePagamento;
    private Double notaSurdo;
    private Double notaInterprete;
    //Indica que a classe Endereço sera mapeada para dentro de Solicitacao
    @Embedded
    private Endereco endereco;

    @Deprecated //Uso do hibernate
    public Solicitacao(){

    };

}
