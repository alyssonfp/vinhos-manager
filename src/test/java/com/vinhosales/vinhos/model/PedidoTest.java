package com.vinhosales.vinhos.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void testPedidoConstructorAndGetters() {
        String id = "1";
        Carrinho carrinho = new Carrinho();
        LocalDateTime dataCriacao = LocalDateTime.now();
        String status = "WAIT_PAYMENT";

        Pedido pedido = new Pedido(id, carrinho, dataCriacao, status);

        assertEquals(id, pedido.getId());
        assertEquals(carrinho, pedido.getCarrinho());
        assertEquals(dataCriacao, pedido.getDataCriacao());
        assertEquals(status, pedido.getStatus());
    }

    @Test
    void testPedidoSetters() {
        Pedido pedido = new Pedido();
        String id = "1";
        Carrinho carrinho = new Carrinho();
        LocalDateTime dataCriacao = LocalDateTime.now();
        String status = "DONE";

        pedido.setId(id);
        pedido.setCarrinho(carrinho);
        pedido.setDataCriacao(dataCriacao);
        pedido.setStatus(status);

        assertEquals(id, pedido.getId());
        assertEquals(carrinho, pedido.getCarrinho());
        assertEquals(dataCriacao, pedido.getDataCriacao());
        assertEquals(status, pedido.getStatus());
    }
}
