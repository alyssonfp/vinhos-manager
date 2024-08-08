package com.vinhosales.vinhos.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PedidoDTOTest {

    @Test
    void testPedidoDTOConstructorAndGetters() {
        String id = "12345";
        CarrinhoDTO carrinho = new CarrinhoDTO();
        LocalDateTime dataCriacao = LocalDateTime.now();
        String status = "WAIT_PAYMENT";

        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(id);
        pedidoDTO.setCarrinho(carrinho);
        pedidoDTO.setDataCriacao(dataCriacao);
        pedidoDTO.setStatus(status);

        assertEquals(id, pedidoDTO.getId());
        assertEquals(carrinho, pedidoDTO.getCarrinho());
        assertEquals(dataCriacao, pedidoDTO.getDataCriacao());
        assertEquals(status, pedidoDTO.getStatus());
    }

    @Test
    void testPedidoDTOSetters() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        String id = "67890";
        CarrinhoDTO carrinho = new CarrinhoDTO();
        LocalDateTime dataCriacao = LocalDateTime.now();
        String status = "DONE";

        pedidoDTO.setId(id);
        pedidoDTO.setCarrinho(carrinho);
        pedidoDTO.setDataCriacao(dataCriacao);
        pedidoDTO.setStatus(status);

        assertEquals(id, pedidoDTO.getId());
        assertEquals(carrinho, pedidoDTO.getCarrinho());
        assertEquals(dataCriacao, pedidoDTO.getDataCriacao());
        assertEquals(status, pedidoDTO.getStatus());
    }

    @Test
    void testPedidoDTOEmptyConstructor() {
        PedidoDTO pedidoDTO = new PedidoDTO();

        assertNull(pedidoDTO.getId());
        assertNull(pedidoDTO.getCarrinho());
        assertNull(pedidoDTO.getDataCriacao());
        assertNull(pedidoDTO.getStatus());
    }
}
