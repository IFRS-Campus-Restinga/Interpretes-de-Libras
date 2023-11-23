package br.com.interpreto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.interpreto.pedido.Pedido;
import br.com.interpreto.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/{pedidoId}/cancelar")
    public ResponseEntity<String> cancelarPedido(@PathVariable Long pedidoId) {
        try {
            pedidoService.cancelarPedido(pedidoId);
            return ResponseEntity.ok("Pedido cancelado com sucesso");
        } catch (EntityNotFoundException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Outros métodos do controlador conforme necessário
}
