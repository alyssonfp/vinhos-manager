package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Carrinho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Collections;

@DataMongoTest
@AutoConfigureDataMongo
class CarrinhoRepositoryTest {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @BeforeEach
    void setUp() {
        carrinhoRepository.deleteAll().block();
    }

    @Test
    void testSaveAndFindById() {
        Carrinho carrinho = new Carrinho();
        carrinho.setId("1");
        carrinho.setProdutos(Collections.emptyList());
        carrinho.setDataCriacao(LocalDateTime.now());
        carrinho.setDataAlteracao(LocalDateTime.now());

        Mono<Carrinho> saveCarrinho = carrinhoRepository.save(carrinho);

        StepVerifier.create(saveCarrinho)
                .expectNextMatches(savedCarrinho -> savedCarrinho.getId().equals("1"))
                .verifyComplete();

        Mono<Carrinho> foundCarrinho = carrinhoRepository.findById("1");

        StepVerifier.create(foundCarrinho)
                .expectNextMatches(found -> found.getId().equals("1"))
                .verifyComplete();
    }

    @Test
    void testDeleteCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.setId("2");
        carrinho.setProdutos(Collections.emptyList());
        carrinho.setDataCriacao(LocalDateTime.now());
        carrinho.setDataAlteracao(LocalDateTime.now());

        carrinhoRepository.save(carrinho).block();

        Mono<Void> deleteCarrinho = carrinhoRepository.deleteById("2");

        StepVerifier.create(deleteCarrinho)
                .verifyComplete();

        Mono<Carrinho> foundCarrinho = carrinhoRepository.findById("2");

        StepVerifier.create(foundCarrinho)
                .expectNextCount(0)
                .verifyComplete();
    }
}

