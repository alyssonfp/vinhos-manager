package com.vinhosales.vinhos.service.impl;

import com.vinhosales.vinhos.model.Produto;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import com.vinhosales.vinhos.service.ProdutoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Flux<Produto> getProdutosPorCategoria(String categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId)
                .filter(produto -> produto.getQuantidade() > 0);
    }

    @Override
    public Mono<Produto> createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Mono<Void> deleteProduto(String id) {
        return produtoRepository.deleteById(id);
    }

    @Override
    public Mono<Produto> updateProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
}
