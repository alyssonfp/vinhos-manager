package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Categoria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoriaRepository extends ReactiveMongoRepository<Categoria, String> {
    Mono<Categoria> findByNome(String nome);
}
