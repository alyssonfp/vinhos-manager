package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Carrinho;
import com.vinhosales.vinhos.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        pedidoRepository.deleteAll().block();
    }

    @Test
    void testSaveAndFindById() {
        Carrinho carrinho = new Carrinho();
        carrinho.setId("carrinho1");

        Pedido pedido = new Pedido();
        pedido.setId("1");
        pedido.setCarrinho(carrinho);
        pedido.setStatus("WAIT_PAYMENT");

        Mono<Pedido> savePedido = pedidoRepository.save(pedido);

        StepVerifier.create(savePedido)
                .expectNextMatches(savedPedido -> savedPedido.getId().equals("1"))
                .verifyComplete();

        Mono<Pedido> foundPedido = pedidoRepository.findById("1");

        StepVerifier.create(foundPedido)
                .expectNextMatches(found -> found.getId().equals("1"))
                .verifyComplete();
    }

    @Test
    void testFindByCarrinhoId() {
        Carrinho carrinho = new Carrinho();
        carrinho.setId("carrinho2");

        Pedido pedido = new Pedido();
        pedido.setId("2");
        pedido.setCarrinho(carrinho);
        pedido.setStatus("WAIT_PAYMENT");

        pedidoRepository.save(pedido).block();

        Mono<Pedido> foundPedido = pedidoRepository.findByCarrinhoId("carrinho2");

        StepVerifier.create(foundPedido)
                .expectNextMatches(found -> found.getCarrinho().getId().equals("carrinho2"))
                .verifyComplete();
    }
}


