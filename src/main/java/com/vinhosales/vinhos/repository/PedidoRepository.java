package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Pedido;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PedidoRepository extends ReactiveMongoRepository<Pedido, String> {
    Mono<Pedido> findByCarrinhoId(String carrinhoId);
}
