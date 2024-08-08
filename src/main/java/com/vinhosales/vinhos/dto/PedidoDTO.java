package com.vinhosales.vinhos.dto;

import java.time.LocalDateTime;

public class PedidoDTO {

    private String id;
    private CarrinhoDTO carrinho;
    private LocalDateTime dataCriacao;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CarrinhoDTO getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDTO carrinho) {
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
