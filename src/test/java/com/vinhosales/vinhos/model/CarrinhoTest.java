package com.vinhosales.vinhos.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {

    @Test
    void testCarrinhoConstructorAndGetters() {
        String id = "123";
        Produto produto = new Produto();
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataAlteracao = LocalDateTime.now();

        Carrinho carrinho = new Carrinho(id, Collections.singletonList(produto), dataCriacao, dataAlteracao);

        assertEquals(id, carrinho.getId());
        assertEquals(1, carrinho.getProdutos().size());
        assertEquals(produto, carrinho.getProdutos().get(0));
        assertEquals(dataCriacao, carrinho.getDataCriacao());
        assertEquals(dataAlteracao, carrinho.getDataAlteracao());
    }

    @Test
    void testCarrinhoSetters() {
        Carrinho carrinho = new Carrinho();
        String id = "123";
        Produto produto = new Produto();
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataAlteracao = LocalDateTime.now();

        carrinho.setId(id);
        carrinho.setProdutos(Collections.singletonList(produto));
        carrinho.setDataCriacao(dataCriacao);
        carrinho.setDataAlteracao(dataAlteracao);

        assertEquals(id, carrinho.getId());
        assertEquals(1, carrinho.getProdutos().size());
        assertEquals(produto, carrinho.getProdutos().get(0));
        assertEquals(dataCriacao, carrinho.getDataCriacao());
        assertEquals(dataAlteracao, carrinho.getDataAlteracao());
    }
}
