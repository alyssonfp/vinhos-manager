package com.vinhosales.vinhos.service.impl;

import com.vinhosales.vinhos.model.Carrinho;
import com.vinhosales.vinhos.repository.CarrinhoRepository;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import com.vinhosales.vinhos.service.CarrinhoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoServiceImpl(CarrinhoRepository carrinhoRepository, ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Mono<Carrinho> createCarrinho(Carrinho carrinho) {
        carrinho.setDataCriacao(LocalDateTime.now());
        carrinho.setDataAlteracao(LocalDateTime.now());
        return carrinhoRepository.save(carrinho)
                .doOnError(error -> {
                    System.err.println("Erro ao criar carrinho: " + error.getMessage());
                });
    }

    @Override
    public Mono<Carrinho> getCarrinhoById(String id) {
        return carrinhoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Carrinho não encontrado com id: " + id)))
                .doOnError(error -> {
                    System.err.println("Erro ao buscar carrinho: " + error.getMessage());
                });
    }

    @Override
    public Mono<Void> deleteCarrinho(String id) {
        return carrinhoRepository.deleteById(id)
                .doOnError(error -> {
                    System.err.println("Erro ao deletar carrinho: " + error.getMessage());
                });
    }

    @Override
    public Mono<Carrinho> addProdutoToCarrinho(String carrinhoId, String produtoId, int quantidade) {
        return carrinhoRepository.findById(carrinhoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Carrinho não encontrado com id: " + carrinhoId)))
                .flatMap(carrinho -> produtoRepository.findById(produtoId)
                        .switchIfEmpty(Mono.error(new RuntimeException("Produto não encontrado com id: " + produtoId)))
                        .filter(produto -> produto.getQuantidade() >= quantidade)
                        .switchIfEmpty(Mono.error(new RuntimeException("Quantidade insuficiente do produto")))
                        .map(produto -> {
                            if (carrinho.getProdutos() == null) {
                                carrinho.setProdutos(new ArrayList<>());
                            }
                            carrinho.getProdutos().add(produto);
                            if (carrinho.getDataCriacao() == null) {
                                carrinho.setDataCriacao(LocalDateTime.now());
                            }
                            carrinho.setDataAlteracao(LocalDateTime.now());
                            return carrinho;
                        })
                )
                .flatMap(carrinhoRepository::save)
                .doOnError(error -> {
                    System.err.println("Erro ao adicionar produto ao carrinho: " + error.getMessage());
                });
    }

    @Override
    public Mono<Carrinho> removeProdutoFromCarrinho(String carrinhoId, String produtoId) {
        return carrinhoRepository.findById(carrinhoId)
                .switchIfEmpty(Mono.error(new RuntimeException("Carrinho não encontrado com id: " + carrinhoId)))
                .map(carrinho -> {
                    if (carrinho.getProdutos() == null) {
                        throw new RuntimeException("Carrinho não possui produtos");
                    }
                    carrinho.getProdutos().removeIf(produto -> produto.getId().equals(produtoId));
                    carrinho.setDataAlteracao(LocalDateTime.now());
                    return carrinho;
                })
                .flatMap(carrinhoRepository::save)
                .doOnError(error -> {
                    System.err.println("Erro ao remover produto do carrinho: " + error.getMessage());
                });
    }
}
