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
}
