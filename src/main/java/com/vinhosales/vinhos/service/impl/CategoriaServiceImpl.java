package com.vinhosales.vinhos.service.impl;

import com.vinhosales.vinhos.model.Categoria;
import com.vinhosales.vinhos.repository.CategoriaRepository;
import com.vinhosales.vinhos.service.CategoriaService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Flux<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Mono<Categoria> getCategoriaById(String id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Mono<Categoria> createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Mono<Void> deleteCategoria(String id) {
        return categoriaRepository.deleteById(id);
    }
}
