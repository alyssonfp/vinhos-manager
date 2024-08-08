package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        produtoRepository.deleteAll().block();

        Produto produto1 = new Produto("1", "Produto 1", "Descrição 1", 10, 100.0, "categoria1");
        Produto produto2 = new Produto("2", "Produto 2", "Descrição 2", 5, 150.0, "categoria2");
        Produto produto3 = new Produto("3", "Produto 3", "Descrição 3", 20, 200.0, "categoria1");

        produtoRepository.saveAll(Flux.just(produto1, produto2, produto3)).blockLast();
    }

    @Test
    void testFindByCategoriaId() {
        Flux<Produto> produtos = produtoRepository.findByCategoriaId("categoria1");

        StepVerifier.create(produtos)
                .expectNextMatches(produto -> produto.getCategoriaId().equals("categoria1"))
                .expectNextMatches(produto -> produto.getCategoriaId().equals("categoria1"))
                .verifyComplete();
    }

    @Test
    void testFindByQuantidadeGreaterThan() {
        Flux<Produto> produtos = produtoRepository.findByQuantidadeGreaterThan(15);

        StepVerifier.create(produtos)
                .expectNextMatches(produto -> produto.getQuantidade() > 15)
                .verifyComplete();
    }
}

