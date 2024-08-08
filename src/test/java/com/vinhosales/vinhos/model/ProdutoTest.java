package com.vinhosales.vinhos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testProdutoConstructorAndGetters() {
        String id = "1";
        String nome = "Vinho Tinto";
        String descricao = "Vinho tinto de excelente qualidade";
        int quantidade = 100;
        double valor = 59.90;
        String categoriaId = "categoria1";

        Produto produto = new Produto(id, nome, descricao, quantidade, valor, categoriaId);

        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertEquals(descricao, produto.getDescricao());
        assertEquals(quantidade, produto.getQuantidade());
        assertEquals(valor, produto.getValor());
        assertEquals(categoriaId, produto.getCategoriaId());
    }

    @Test
    void testProdutoSetters() {
        Produto produto = new Produto();
        String id = "1";
        String nome = "Vinho Branco";
        String descricao = "Vinho branco de excelente qualidade";
        int quantidade = 50;
        double valor = 45.90;
        String categoriaId = "categoria2";

        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setQuantidade(quantidade);
        produto.setValor(valor);
        produto.setCategoriaId(categoriaId);

        assertEquals(id, produto.getId());
        assertEquals(nome, produto.getNome());
        assertEquals(descricao, produto.getDescricao());
        assertEquals(quantidade, produto.getQuantidade());
        assertEquals(valor, produto.getValor());
        assertEquals(categoriaId, produto.getCategoriaId());
    }
}
