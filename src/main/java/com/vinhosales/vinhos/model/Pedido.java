package com.vinhosales.vinhos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String id;
    private Carrinho carrinho;
    private LocalDateTime dataCriacao;
    private String status;

    public Pedido() {
    }

    public Pedido(String id, Carrinho carrinho, LocalDateTime dataCriacao, String status) {
        this.id = id;
        this.carrinho = carrinho;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
