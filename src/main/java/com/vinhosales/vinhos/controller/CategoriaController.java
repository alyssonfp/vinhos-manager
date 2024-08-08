package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Categoria;
import com.vinhosales.vinhos.service.CategoriaService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public Flux<Categoria> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    public Mono<Categoria> getCategoriaById(@PathVariable String id) {
        return categoriaService.getCategoriaById(id);
    }

    @PostMapping
    public Mono<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.createCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCategoria(@PathVariable String id) {
        return categoriaService.deleteCategoria(id);
    }
}
