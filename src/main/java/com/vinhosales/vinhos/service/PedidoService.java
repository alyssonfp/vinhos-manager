package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.model.Pedido;
import reactor.core.publisher.Mono;

public interface PedidoService {
    Mono<Pedido> createPedido(Pedido pedido);
    Mono<Pedido> getPedidoByCarrinhoId(String carrinhoId);
}
