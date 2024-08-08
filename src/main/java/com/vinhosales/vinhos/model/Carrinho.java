package com.vinhosales.vinhos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "carrinhos")
public class Carrinho {
    @Id
    private String id;
    private List<Produto> produtos;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;

    public Carrinho() {
    }

    public Carrinho(String id, List<Produto> produtos, LocalDateTime dataCriacao, LocalDateTime dataAlteracao) {
        this.id = id;
        this.produtos = produtos;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
