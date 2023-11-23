package br.com.interpreto.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(Pedido pedido);

    // Outros métodos de consulta conforme necessário
}
