package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Carrinho;
import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.service.CarrinhoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CarrinhoControllerTest {

    @Mock
    private CarrinhoService carrinhoService;

    @InjectMocks
    private CarrinhoController carrinhoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCarrinho() {
        Carrinho carrinho = new Carrinho("1", Collections.emptyList(), null, null);
        when(carrinhoService.createCarrinho(any(Carrinho.class))).thenReturn(Mono.just(carrinho));

        Mono<Carrinho> result = carrinhoController.createCarrinho(carrinho);

        StepVerifier.create(result)
                .expectNext(carrinho)
                .verifyComplete();
    }

    @Test
    void getCarrinhoById() {
        Carrinho carrinho = new Carrinho("1", Collections.emptyList(), null, null);
        when(carrinhoService.getCarrinhoById(anyString())).thenReturn(Mono.just(carrinho));

        Mono<Carrinho> result = carrinhoController.getCarrinhoById("1");

        StepVerifier.create(result)
                .expectNext(carrinho)
                .verifyComplete();
    }

    @Test
    void deleteCarrinho() {
        when(carrinhoService.deleteCarrinho(anyString())).thenReturn(Mono.empty());

        Mono<Void> result = carrinhoController.deleteCarrinho("1");

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void addProdutoToCarrinho() {
        Carrinho carrinho = new Carrinho("1", Collections.singletonList(new Produto()), null, null);
        when(carrinhoService.addProdutoToCarrinho(anyString(), anyString(), eq(2))).thenReturn(Mono.just(carrinho));

        Mono<Carrinho> result = carrinhoController.addProdutoToCarrinho("1", "prod1", 2);

        StepVerifier.create(result)
                .expectNext(carrinho)
                .verifyComplete();
    }

    @Test
    void removeProdutoFromCarrinho() {
        Carrinho carrinho = new Carrinho("1", Collections.emptyList(), null, null);
        when(carrinhoService.removeProdutoFromCarrinho(anyString(), anyString())).thenReturn(Mono.just(carrinho));

        Mono<Carrinho> result = carrinhoController.removeProdutoFromCarrinho("1", "prod1");

        StepVerifier.create(result)
                .expectNext(carrinho)
                .verifyComplete();
    }
}

