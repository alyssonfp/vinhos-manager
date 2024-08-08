package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Produto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProdutoRepository extends ReactiveMongoRepository<Produto, String > {
    Flux<Produto> findByCategoriaId(String categoriaId);
    Flux<Produto> findByQuantidadeGreaterThan(int quantidade);
}
