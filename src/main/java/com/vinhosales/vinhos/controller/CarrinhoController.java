package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Carrinho;
import com.vinhosales.vinhos.service.CarrinhoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    public Mono<Carrinho> createCarrinho(@RequestBody Carrinho carrinho) {
        return carrinhoService.createCarrinho(carrinho);
    }

    @GetMapping("/{id}")
    public Mono<Carrinho> getCarrinhoById(@PathVariable String id) {
        return carrinhoService.getCarrinhoById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCarrinho(@PathVariable String id) {
        return carrinhoService.deleteCarrinho(id);
    }

    @PutMapping("/{id}/produto/{produtoId}")
    public Mono<Carrinho> addProdutoToCarrinho(@PathVariable String id, @PathVariable String produtoId, @RequestParam int quantidade) {
        return carrinhoService.addProdutoToCarrinho(id, produtoId, quantidade);
    }

    @DeleteMapping("/{id}/produto/{produtoId}")
    public Mono<Carrinho> removeProdutoFromCarrinho(@PathVariable String id, @PathVariable String produtoId) {
        return carrinhoService.removeProdutoFromCarrinho(id, produtoId);
    }
}
