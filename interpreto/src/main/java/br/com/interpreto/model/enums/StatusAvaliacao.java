package br.com.interpreto.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusAvaliacao {
    DEFERIDO,
    INDEFERIDO,
    ANALISANDO;
    @JsonCreator
    public static StatusAvaliacao fromString(String value) {
        for (StatusAvaliacao statusAvaliacao : StatusAvaliacao.values()) {
            if (statusAvaliacao.name().equalsIgnoreCase(value)) {
                return statusAvaliacao;
            }
        }
        throw new IllegalArgumentException("Status de Avaliacão inválida: " + value);
    }
}
