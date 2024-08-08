package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import com.vinhosales.vinhos.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProdutoServiceImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produto = new Produto("1", "Vinho Tinto", "Vinho de qualidade", 10, 50.0, "categoria1");
    }

    @Test
    void getProdutosPorCategoria_ShouldReturnAvailableProdutos() {
        when(produtoRepository.findByCategoriaId(anyString())).thenReturn(Flux.just(produto));

        Flux<Produto> result = produtoService.getProdutosPorCategoria("categoria1");

        StepVerifier.create(result)
                .expectNext(produto)
                .verifyComplete();

        verify(produtoRepository, times(1)).findByCategoriaId("categoria1");
    }

    @Test
    void createProduto_ShouldSaveProduto() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(Mono.just(produto));

        Mono<Produto> result = produtoService.createProduto(produto);

        StepVerifier.create(result)
                .expectNext(produto)
                .verifyComplete();

        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void deleteProduto_ShouldDeleteProduto() {
        when(produtoRepository.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<Void> result = produtoService.deleteProduto("1");

        StepVerifier.create(result)
                .verifyComplete();

        verify(produtoRepository, times(1)).deleteById("1");
    }

    @Test
    void updateProduto_ShouldUpdateProduto() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(Mono.just(produto));

        Mono<Produto> result = produtoService.updateProduto(produto);

        StepVerifier.create(result)
                .expectNext(produto)
                .verifyComplete();

        verify(produtoRepository, times(1)).save(produto);
    }
}
