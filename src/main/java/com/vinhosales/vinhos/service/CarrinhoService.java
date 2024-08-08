package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.model.Carrinho;
import reactor.core.publisher.Mono;

public interface CarrinhoService {
    Mono<Carrinho> createCarrinho(Carrinho carrinho);
    Mono<Carrinho> getCarrinhoById(String id);
    Mono<Void> deleteCarrinho(String id);
    Mono<Carrinho> addProdutoToCarrinho(String carrinhoId, String produtoId, int quantidade);
    Mono<Carrinho> removeProdutoFromCarrinho(String carrinhoId, String produtoId);
}
