package com.vinhosales.vinhos.repository;

import com.vinhosales.vinhos.model.Carrinho;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface CarrinhoRepository extends ReactiveMongoRepository<Carrinho, String> {

}
