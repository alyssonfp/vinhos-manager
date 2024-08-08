package com.vinhosales.vinhos.service.impl;

import com.vinhosales.vinhos.config.RabbitMQConfig;
import com.vinhosales.vinhos.dto.PagamentoDTO;
import com.vinhosales.vinhos.model.Pedido;
import com.vinhosales.vinhos.repository.PedidoRepository;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import com.vinhosales.vinhos.service.PedidoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final RabbitTemplate rabbitTemplate;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, RabbitTemplate rabbitTemplate) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Mono<Pedido> createPedido(Pedido pedido) {
        if (pedido.getCarrinho() == null || pedido.getCarrinho().getProdutos() == null || pedido.getCarrinho().getProdutos().isEmpty()) {
            return Mono.error(new RuntimeException("Carrinho está vazio ou não configurado"));
        }

        return Flux.fromIterable(pedido.getCarrinho().getProdutos())
                .flatMap(produto -> produtoRepository.findById(produto.getId())
                        .map(produtoDb -> {
                            produto.setNome(produtoDb.getNome());
                            produto.setDescricao(produtoDb.getDescricao());
                            produto.setValor(produtoDb.getValor());
                            produto.setCategoriaId(produtoDb.getCategoriaId());
                            return produto;
                        })
                )
                .collectList()
                .flatMap(produtos -> {
                    pedido.getCarrinho().setProdutos(produtos);

                    if (pedido.getCarrinho().getDataCriacao() == null) {
                        pedido.getCarrinho().setDataCriacao(LocalDateTime.now());
                    }
                    pedido.getCarrinho().setDataAlteracao(LocalDateTime.now());

                    return verificarDisponibilidade(pedido)
                            .flatMap(disponivel -> {
                                if (disponivel) {
                                    pedido.setDataCriacao(LocalDateTime.now());
                                    return pedidoRepository.save(pedido)
                                            .doOnSuccess(savedPedido -> {
                                                PagamentoDTO pagamentoDTO = new PagamentoDTO(
                                                        savedPedido.getId(),
                                                        "WAIT_PAYMENT",
                                                        LocalDateTime.now()
                                                );
                                                rabbitTemplate.convertAndSend(
                                                        RabbitMQConfig.EXCHANGE,
                                                        RabbitMQConfig.ROUTING_KEY,
                                                        pagamentoDTO);
                                            })
                                            .doOnError(error -> {
                                                System.err.println("Erro ao salvar pedido: " + error.getMessage());
                                            });
                                } else {
                                    return Mono.error(new RuntimeException("Produtos indisponíveis"));
                                }
                            });
                });
    }

    private Mono<Boolean> verificarDisponibilidade(Pedido pedido) {
        return Flux.fromIterable(pedido.getCarrinho().getProdutos())
                .flatMap(produto -> produtoRepository.findById(produto.getId())
                        .map(produtoDb -> {
                            if (produtoDb.getQuantidade() < produto.getQuantidade()) {
                                throw new IllegalArgumentException("Produto " + produto.getNome() + " está indisponível na quantidade solicitada.");
                            }
                            return true;
                        })
                )
                .all(disponivel -> disponivel);
    }

    @Override
    public Mono<Pedido> getPedidoByCarrinhoId(String carrinhoId) {
        return pedidoRepository.findByCarrinhoId(carrinhoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido não encontrado com carrinhoId: " + carrinhoId)))
                .doOnError(error -> {
                    System.err.println("Erro ao buscar pedido: " + error.getMessage());
                });
    }
}
