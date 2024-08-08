package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Pedido;
import com.vinhosales.vinhos.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(pedidoController).build();
    }

    @Test
    void testCreatePedido() {
        Pedido pedido = new Pedido("1", null, null, "WAIT_PAYMENT");
        when(pedidoService.createPedido(any(Pedido.class))).thenReturn(Mono.just(pedido));

        webTestClient.post().uri("/pedidos")
                .bodyValue(pedido)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Pedido.class)
                .value(responsePedido -> {
                    assertEquals(pedido.getId(), responsePedido.getId());
                    assertEquals(pedido.getStatus(), responsePedido.getStatus());
                });
    }

    @Test
    void testGetPedidoByCarrinhoId() {
        Pedido pedido = new Pedido("1", null, null, "WAIT_PAYMENT");
        when(pedidoService.getPedidoByCarrinhoId(anyString())).thenReturn(Mono.just(pedido));

        webTestClient.get().uri("/pedidos/carrinho/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Pedido.class)
                .value(responsePedido -> {
                    assertEquals(pedido.getId(), responsePedido.getId());
                    assertEquals(pedido.getStatus(), responsePedido.getStatus());
                });
    }
}
