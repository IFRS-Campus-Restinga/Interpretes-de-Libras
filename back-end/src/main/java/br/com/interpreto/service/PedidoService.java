package br.com.interpreto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.interpreto.pedido.Pedido;
import br.com.interpreto.pedido.PedidoRepository;
import br.com.interpreto.pedido.StatusPedido;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        if (pedido.getStatus() == StatusPedido.PENDENTE || pedido.getStatus() == StatusPedido.PROCESSAMENTO) {
            pedido.setStatus(StatusPedido.CANCELADO);
            return pedidoRepository.save(pedido);
        } else {
            throw new IllegalStateException("Não é possível cancelar um pedido concluído ou cancelado.");
        }
    }

    // Outros métodos de serviço conforme necessário
}

