package br.com.interpreto.model.solicitacao;

import br.com.interpreto.model.documento.Documento;
import br.com.interpreto.model.enums.Especialidade;
import br.com.interpreto.model.enums.Regiao;
import br.com.interpreto.model.enums.StatusSolicitacao;
import br.com.interpreto.model.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;

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
    @Temporal(TemporalType.DATE)
    private LocalDate dataEncontro;
    @Temporal(TemporalType.TIME)
    private LocalTime horaCriacao;
    private int duracaoAtendimento;
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao statusSolicitacao;
    @Column(columnDefinition = "TEXT")
    private String observacaoSolicitacao;
    @OneToOne
    @JoinColumn(name = "documento_id")
    private Documento comprovantePagamento;
    private Double notaSurdo;
    private Double notaInterprete;
    @Embedded //Indica que a classe Endereço sera mapeada para dentro de Solicitacao
    private Endereco endereco;

    //Falta ligação com surdo e interprete

    @Deprecated //Uso do hibernate
    public Solicitacao(){

    };
    public Solicitacao(@Valid SolicitacaoCadastroDTO dados) {
        this.dataCriacao = LocalDate.now();
        this.horaCriacao = LocalTime.now();
        this.statusSolicitacao = StatusSolicitacao.ABERTA;
        this.dataEncontro = dados.dataEncontro();
        this.especialidade = dados.especialidades();
        this.regioes = dados.regioes();
        this.duracaoAtendimento = dados.duracaoAtendimento();
        this.endereco = dados.endereco();
        this.observacaoSolicitacao = dados.observacaoSolicitacao();

    }
    public void solicitacaoAtualizarDTO(@Valid SolicitacaoAtualizaDTO novosDados) {
        this.duracaoAtendimento = novosDados.duracaoAtendimento();
    }
    public Long getId() {
        return id;
    }

    public Set<Regiao> getRegioes() {
        return regioes;
    }

    public void setRegioes(Set<Regiao> regioes) {
        this.regioes = regioes;
    }

    public Set<Especialidade> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Set<Especialidade> especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalTime getHoraCriacao() {
        return horaCriacao;
    }

    public void setHoraCriacao(LocalTime horaCriacao) {
        this.horaCriacao = horaCriacao;
    }

    public int getDuracaoAtendimento() {
        return duracaoAtendimento;
    }

    public void setDuracaoAtendimento(int duracaoAtendimento) {
        this.duracaoAtendimento = duracaoAtendimento;
    }

    public StatusSolicitacao getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public String getObservacaoSolicitacao() {
        return observacaoSolicitacao;
    }

    public void setObservacaoSolicitacao(String observacaoSolicitacao) {
        this.observacaoSolicitacao = observacaoSolicitacao;
    }

    public Documento getComprovantePagamento() {
        return comprovantePagamento;
    }

    public void setComprovantePagamento(Documento comprovantePagamento) {
        this.comprovantePagamento = comprovantePagamento;
    }

    public Double getNotaSurdo() {
        return notaSurdo;
    }

    public void setNotaSurdo(Double notaSurdo) {
        this.notaSurdo = notaSurdo;
    }

    public Double getNotaInterprete() {
        return notaInterprete;
    }

    public void setNotaInterprete(Double notaInterprete) {
        this.notaInterprete = notaInterprete;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataEncontro() {
        return dataEncontro;
    }

    public void setDataEncontro(LocalDate dataEncontro) {
        this.dataEncontro = dataEncontro;
    }
}
