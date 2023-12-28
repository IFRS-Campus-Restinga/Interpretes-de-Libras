package br.com.interpreto.model.endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String rua;
    private String cidade;
    @Column(nullable = true)
    private int numero;
    private String complemento;
    private String bairro;
    private String observacaoEndereco;
    private String pontoReferencia;
    private String cep;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getObservacaoEndereco() {
        return observacaoEndereco;
    }

    public void setObservacaoEndereco(String observacaoEndereco) {
        this.observacaoEndereco = observacaoEndereco;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
