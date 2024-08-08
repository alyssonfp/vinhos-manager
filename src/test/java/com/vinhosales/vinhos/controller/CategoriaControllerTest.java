package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Categoria;
import com.vinhosales.vinhos.service.CategoriaService;
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

class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(categoriaController).build();
    }

    @Test
    void testGetAllCategorias() {
        Categoria categoria = new Categoria("1", "Vinhos", "Descrição de Vinhos");

        when(categoriaService.getAllCategorias()).thenReturn(Flux.just(categoria));

        webTestClient.get().uri("/categorias")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Categoria.class)
                .hasSize(1)
                .value(categorias -> {
                    Categoria resultado = categorias.get(0);
                    assertEquals(categoria.getId(), resultado.getId());
                    assertEquals(categoria.getNome(), resultado.getNome());
                    assertEquals(categoria.getDescricao(), resultado.getDescricao());
                });
    }

    @Test
    void testGetCategoriaById() {
        Categoria categoria = new Categoria("1", "Vinhos", "Descrição de Vinhos");

        when(categoriaService.getCategoriaById(anyString())).thenReturn(Mono.just(categoria));

        webTestClient.get().uri("/categorias/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Categoria.class)
                .value(categoriaResposta -> {
                    assertEquals(categoria.getId(), categoriaResposta.getId());
                    assertEquals(categoria.getNome(), categoriaResposta.getNome());
                    assertEquals(categoria.getDescricao(), categoriaResposta.getDescricao());
                });
    }

    @Test
    void testCreateCategoria() {
        Categoria categoria = new Categoria("1", "Vinhos", "Descrição de Vinhos");

        when(categoriaService.createCategoria(any(Categoria.class))).thenReturn(Mono.just(categoria));

        webTestClient.post().uri("/categorias")
                .bodyValue(categoria)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Categoria.class)
                .value(categoriaResposta -> {
                    assertEquals(categoria.getId(), categoriaResposta.getId());
                    assertEquals(categoria.getNome(), categoriaResposta.getNome());
                    assertEquals(categoria.getDescricao(), categoriaResposta.getDescricao());
                });
    }

    @Test
    void testDeleteCategoria() {
        when(categoriaService.deleteCategoria(anyString())).thenReturn(Mono.empty());

        webTestClient.delete().uri("/categorias/1")
                .exchange()
                .expectStatus().isOk();
    }
}
