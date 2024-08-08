package com.vinhosales.vinhos.service;

import com.vinhosales.vinhos.model.Categoria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaService {
    Flux<Categoria> getAllCategorias();
    Mono<Categoria> getCategoriaById(String id);
    Mono<Categoria> createCategoria(Categoria categoria);
    Mono<Void> deleteCategoria(String id);
}
