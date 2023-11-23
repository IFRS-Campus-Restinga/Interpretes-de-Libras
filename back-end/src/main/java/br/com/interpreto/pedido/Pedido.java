package br.com.interpreto.pedido;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Outros campos...

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public StatusPedido getStatus() {
        return null;
    }

    public void setStatus(StatusPedido cancelado) {
    }

    // Getters e Setters...
}
