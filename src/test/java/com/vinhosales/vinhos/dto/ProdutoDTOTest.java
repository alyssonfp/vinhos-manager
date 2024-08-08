package com.vinhosales.vinhos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoDTOTest {

    @Test
    void testProdutoDTO() {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId("123");
        produtoDTO.setNome("Vinho Tinto");
        produtoDTO.setDescricao("Vinho de qualidade");
        produtoDTO.setQuantidade(10);
        produtoDTO.setValor(50.0);

        assertEquals("123", produtoDTO.getId());
        assertEquals("Vinho Tinto", produtoDTO.getNome());
        assertEquals("Vinho de qualidade", produtoDTO.getDescricao());
        assertEquals(10, produtoDTO.getQuantidade());
        assertEquals(50.0, produtoDTO.getValor());
    }

    @Test
    void testProdutoDTOSetters() {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId("456");
        produtoDTO.setNome("Vinho Branco");
        produtoDTO.setDescricao("Vinho branco seco");
        produtoDTO.setQuantidade(5);
        produtoDTO.setValor(75.0);

        assertEquals("456", produtoDTO.getId());
        assertEquals("Vinho Branco", produtoDTO.getNome());
        assertEquals("Vinho branco seco", produtoDTO.getDescricao());
        assertEquals(5, produtoDTO.getQuantidade());
        assertEquals(75.0, produtoDTO.getValor());
    }
}

