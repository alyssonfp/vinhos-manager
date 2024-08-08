package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.model.Carrinho;
import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.repository.CarrinhoRepository;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import com.vinhosales.vinhos.service.impl.CarrinhoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Collections;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CarrinhoServiceImplTest {

    @Mock
    private CarrinhoRepository carrinhoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private CarrinhoServiceImpl carrinhoService;

    private Carrinho carrinho;
    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produto = new Produto("1", "Vinho", "Vinho Tinto", 10, 100.0, "vinhosTintos");
        carrinho = new Carrinho("1", Collections.singletonList(produto), LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void createCarrinho_ShouldReturnCreatedCarrinho() {
        when(carrinhoRepository.save(any(Carrinho.class))).thenReturn(Mono.just(carrinho));

        Mono<Carrinho> result = carrinhoService.createCarrinho(carrinho);

        StepVerifier.create(result)
                .expectNext(carrinho)
                .verifyComplete();
    }

    @Test
    void getCarrinhoById_ShouldReturnCarrinho_WhenCarrinhoExists() {
        when(carrinhoRepository.findById(anyString())).thenReturn(Mono.just(carrinho));

        Mono<Carrinho> result = carrinhoService.getCarrinhoById("1");

        StepVerifier.create(result)
                .expectNext(carrinho)
                .verifyComplete();
    }

    @Test
    void deleteCarrinho_ShouldComplete_WhenCarrinhoDeleted() {
        when(carrinhoRepository.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<Void> result = carrinhoService.deleteCarrinho("1");

        StepVerifier.create(result)
                .verifyComplete();
    }
}
