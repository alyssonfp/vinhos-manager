package com.vinhosales.vinhos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaDTOTest {

    @Test
    void testCategoriaDTOConstructorAndGetters() {
        String id = "1";
        String nome = "Vinhos Tintos";
        String descricao = "Vinhos encorpados e elegantes.";

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(id);
        categoriaDTO.setNome(nome);
        categoriaDTO.setDescricao(descricao);

        assertEquals(id, categoriaDTO.getId());
        assertEquals(nome, categoriaDTO.getNome());
        assertEquals(descricao, categoriaDTO.getDescricao());
    }

    @Test
    void testCategoriaDTOSetters() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        String id = "2";
        String nome = "Vinhos Brancos";
        String descricao = "Vinhos leves e refrescantes.";

        categoriaDTO.setId(id);
        categoriaDTO.setNome(nome);
        categoriaDTO.setDescricao(descricao);

        assertEquals(id, categoriaDTO.getId());
        assertEquals(nome, categoriaDTO.getNome());
        assertEquals(descricao, categoriaDTO.getDescricao());
    }
}
