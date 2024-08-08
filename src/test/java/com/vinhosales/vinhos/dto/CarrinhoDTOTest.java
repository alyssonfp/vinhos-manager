package com.vinhosales.vinhos.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoDTOTest {

    @Test
    void testCarrinhoDTOConstructorAndGetters() {
        String id = "1";
        List<ProdutoDTO> produtos = new ArrayList<>();
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataAlteracao = LocalDateTime.now();

        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        carrinhoDTO.setId(id);
        carrinhoDTO.setProdutos(produtos);
        carrinhoDTO.setDataCriacao(dataCriacao);
        carrinhoDTO.setDataAlteracao(dataAlteracao);

        assertEquals(id, carrinhoDTO.getId());
        assertEquals(produtos, carrinhoDTO.getProdutos());
        assertEquals(dataCriacao, carrinhoDTO.getDataCriacao());
        assertEquals(dataAlteracao, carrinhoDTO.getDataAlteracao());
    }

    @Test
    void testCarrinhoDTOSetters() {
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        String id = "2";
        List<ProdutoDTO> produtos = new ArrayList<>();
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataAlteracao = LocalDateTime.now();

        carrinhoDTO.setId(id);
        carrinhoDTO.setProdutos(produtos);
        carrinhoDTO.setDataCriacao(dataCriacao);
        carrinhoDTO.setDataAlteracao(dataAlteracao);

        assertEquals(id, carrinhoDTO.getId());
        assertEquals(produtos, carrinhoDTO.getProdutos());
        assertEquals(dataCriacao, carrinhoDTO.getDataCriacao());
        assertEquals(dataAlteracao, carrinhoDTO.getDataAlteracao());
    }
}
