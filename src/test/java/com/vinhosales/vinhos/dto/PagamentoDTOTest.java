package com.vinhosales.vinhos.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoDTOTest {

    @Test
    void testPagamentoDTOConstructorAndGetters() {
        String pedidoId = "12345";
        String status = "DONE";
        LocalDateTime dataCriacao = LocalDateTime.now();

        PagamentoDTO pagamentoDTO = new PagamentoDTO(pedidoId, status, dataCriacao);

        assertEquals(pedidoId, pagamentoDTO.getPedidoId());
        assertEquals(status, pagamentoDTO.getStatus());
        assertEquals(dataCriacao, pagamentoDTO.getDataCriacao());
    }

    @Test
    void testPagamentoDTOSetters() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        String pedidoId = "67890";
        String status = "ERROR_PAYMENT";
        LocalDateTime dataCriacao = LocalDateTime.now();

        pagamentoDTO.setPedidoId(pedidoId);
        pagamentoDTO.setStatus(status);
        pagamentoDTO.setDataCriacao(dataCriacao);

        assertEquals(pedidoId, pagamentoDTO.getPedidoId());
        assertEquals(status, pagamentoDTO.getStatus());
        assertEquals(dataCriacao, pagamentoDTO.getDataCriacao());
    }

    @Test
    void testPagamentoDTOEmptyConstructor() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        assertNull(pagamentoDTO.getPedidoId());
        assertNull(pagamentoDTO.getStatus());
        assertNull(pagamentoDTO.getDataCriacao());
    }
}
