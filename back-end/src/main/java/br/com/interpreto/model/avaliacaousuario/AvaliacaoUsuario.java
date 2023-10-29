package br.com.interpreto.model.avaliacaousuario;

import br.com.interpreto.model.documento.Documento;
import br.com.interpreto.model.enums.StatusAvaliacao;
import br.com.interpreto.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;


@Entity
@Table(name = "avaliacao_usuario")
public class AvaliacaoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String msg;
    @Temporal(TemporalType.DATE)
    private LocalDate dataCriacao;
    @Temporal(TemporalType.DATE)
    private LocalDate dataResposta;
    @Enumerated(EnumType.STRING)
    private StatusAvaliacao statusAvaliacao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false) // Coluna que faz a associação
    @JsonBackReference
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;


    public AvaliacaoUsuario(@Valid AvaliacaoUsuarioCadastroDTO dados) {
        this.usuario = dados.usuario();
        this.dataCriacao = LocalDate.now();
        this.statusAvaliacao = StatusAvaliacao.valueOf("ANALISANDO");

    }
    public void avaliacaoUsuarioAtualizarDTO(@Valid AvaliacaoUsuarioAtualizaDTO novosDados) {
        this.msg = novosDados.msg();
        this.dataResposta = LocalDate.now();
        this.statusAvaliacao = novosDados.statusAvaliacao();
    }

    @Deprecated //Funcionamento do Hibernate
    public AvaliacaoUsuario() {
    }

    public Long getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDate dataResposta) {
        this.dataResposta = dataResposta;
    }

    public StatusAvaliacao getStatusAvaliacao() {
        return statusAvaliacao;
    }

    public void setStatusAvaliacao(StatusAvaliacao statusAvaliacao) {
        this.statusAvaliacao = statusAvaliacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
}

