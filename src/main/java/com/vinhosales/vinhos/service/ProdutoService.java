package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.model.Produto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProdutoService {
    Flux<Produto> getProdutosPorCategoria(String categoriaId);
    Mono<Produto> createProduto(Produto produto);
    Mono<Void> deleteProduto(String id);
    Mono<Produto> updateProduto(Produto produto);
}
