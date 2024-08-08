package com.vinhosales.vinhos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    void testCategoriaConstructorAndGetters() {
        String id = "1";
        String nome = "Vinhos Tintos";
        String descricao = "Vinhos encorpados e elegantes.";

        Categoria categoria = new Categoria(id, nome, descricao);

        assertEquals(id, categoria.getId());
        assertEquals(nome, categoria.getNome());
        assertEquals(descricao, categoria.getDescricao());
    }

    @Test
    void testCategoriaSetters() {
        Categoria categoria = new Categoria();
        String id = "1";
        String nome = "Vinhos Brancos";
        String descricao = "Vinhos leves e refrescantes.";

        categoria.setId(id);
        categoria.setNome(nome);
        categoria.setDescricao(descricao);

        assertEquals(id, categoria.getId());
        assertEquals(nome, categoria.getNome());
        assertEquals(descricao, categoria.getDescricao());
    }
}
