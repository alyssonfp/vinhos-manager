package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.service.ProdutoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/categoria/{categoriaId}")
    public Flux<Produto> getProdutosPorCategoria(@PathVariable String categoriaId) {
        return produtoService.getProdutosPorCategoria(categoriaId);
    }

    @PostMapping
    public Mono<Produto> createProduto(@RequestBody Produto produto) {
        return produtoService.createProduto(produto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduto(@PathVariable String id) {
        return produtoService.deleteProduto(id);
    }

    @PutMapping("/{id}")
    public Mono<Produto> updateProduto(@PathVariable String id, @RequestBody Produto produto) {
        produto.setId(id);
        return produtoService.updateProduto(produto);
    }
}
