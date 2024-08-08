package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(produtoController).build();
    }

    @Test
    void testGetProdutosPorCategoria() {
        Produto produto = new Produto("1", "Vinho Tinto", "Vinho de qualidade", 10, 50.0, "categoria1");
        when(produtoService.getProdutosPorCategoria(anyString())).thenReturn(Flux.just(produto));

        webTestClient.get().uri("/produtos/categoria/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Produto.class)
                .hasSize(1)
                .value(produtos -> {
                    Produto resultado = produtos.get(0);
                    assertEquals(produto.getId(), resultado.getId());
                    assertEquals(produto.getNome(), resultado.getNome());
                    assertEquals(produto.getDescricao(), resultado.getDescricao());
                    assertEquals(produto.getQuantidade(), resultado.getQuantidade());
                    assertEquals(produto.getValor(), resultado.getValor());
                    assertEquals(produto.getCategoriaId(), resultado.getCategoriaId());
                });
    }

    @Test
    void testCreateProduto() {
        Produto produto = new Produto("1", "Vinho Tinto", "Vinho de qualidade", 10, 50.0, "categoria1");
        when(produtoService.createProduto(any(Produto.class))).thenReturn(Mono.just(produto));

        webTestClient.post().uri("/produtos")
                .bodyValue(produto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Produto.class)
                .value(produtoResposta -> {
                    assertEquals(produto.getId(), produtoResposta.getId());
                    assertEquals(produto.getNome(), produtoResposta.getNome());
                    assertEquals(produto.getDescricao(), produtoResposta.getDescricao());
                    assertEquals(produto.getQuantidade(), produtoResposta.getQuantidade());
                    assertEquals(produto.getValor(), produtoResposta.getValor());
                    assertEquals(produto.getCategoriaId(), produtoResposta.getCategoriaId());
                });
    }

    @Test
    void testDeleteProduto() {
        when(produtoService.deleteProduto(anyString())).thenReturn(Mono.empty());

        webTestClient.delete().uri("/produtos/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    void testUpdateProduto() {
        Produto produto = new Produto("1", "Vinho Tinto", "Vinho de qualidade", 10, 50.0, "categoria1");
        when(produtoService.updateProduto(any(Produto.class))).thenReturn(Mono.just(produto));

        webTestClient.put().uri("/produtos/1")
                .bodyValue(produto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Produto.class)
                .value(produtoResposta -> {
                    assertEquals(produto.getId(), produtoResposta.getId());
                    assertEquals(produto.getNome(), produtoResposta.getNome());
                    assertEquals(produto.getDescricao(), produtoResposta.getDescricao());
                    assertEquals(produto.getQuantidade(), produtoResposta.getQuantidade());
                    assertEquals(produto.getValor(), produtoResposta.getValor());
                    assertEquals(produto.getCategoriaId(), produtoResposta.getCategoriaId());
                });
    }
}
