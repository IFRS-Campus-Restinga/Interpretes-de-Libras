package br.com.interpreto.model.endereco;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String rua;
    private String cidade;
    private int numero;
    private String complemento;
    private String bairro;
    private String observacao;
    private String pontoReferencia;
    private String cep;
}
