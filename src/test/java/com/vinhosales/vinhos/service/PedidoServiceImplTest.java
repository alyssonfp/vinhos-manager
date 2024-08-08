package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.config.RabbitMQConfig;
import com.vinhosales.vinhos.dto.PagamentoDTO;
import com.vinhosales.vinhos.model.Carrinho;
import com.vinhosales.vinhos.model.Pedido;
import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.repository.PedidoRepository;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import com.vinhosales.vinhos.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private Pedido pedido;
    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produto = new Produto("1", "Vinho Tinto", "Vinho de qualidade", 10, 50.0, "categoria1");

        Carrinho carrinho = new Carrinho();
        carrinho.setProdutos(List.of(produto));
        carrinho.setDataCriacao(LocalDateTime.now());
        carrinho.setDataAlteracao(LocalDateTime.now());

        pedido = new Pedido("1", carrinho, LocalDateTime.now(), "WAIT_PAYMENT");
    }

    @Test
    void createPedido_ShouldCreateAndSendMessage_WhenProdutosDisponiveis() {
        when(produtoRepository.findById(anyString())).thenReturn(Mono.just(produto));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(Mono.just(pedido));

        Mono<Pedido> result = pedidoService.createPedido(pedido);

        StepVerifier.create(result)
                .expectNext(pedido)
                .verifyComplete();

        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConfig.EXCHANGE),
                eq(RabbitMQConfig.ROUTING_KEY),
                any(PagamentoDTO.class));
    }


    @Test
    void getPedidoByCarrinhoId_ShouldReturnPedido_WhenFound() {
        when(pedidoRepository.findByCarrinhoId(anyString())).thenReturn(Mono.just(pedido));

        Mono<Pedido> result = pedidoService.getPedidoByCarrinhoId("1");

        StepVerifier.create(result)
                .expectNext(pedido)
                .verifyComplete();
    }

    @Test
    void getPedidoByCarrinhoId_ShouldReturnError_WhenNotFound() {
        when(pedidoRepository.findByCarrinhoId(anyString())).thenReturn(Mono.empty());

        Mono<Pedido> result = pedidoService.getPedidoByCarrinhoId("1");

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }
}
