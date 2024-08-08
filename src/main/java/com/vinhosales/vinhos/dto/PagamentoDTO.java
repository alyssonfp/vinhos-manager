package com.vinhosales.vinhos.dto;

import java.time.LocalDateTime;

public class PagamentoDTO {

    private String pedidoId;
    private String status;
    private LocalDateTime dataCriacao;

    public PagamentoDTO() {
    }

    public PagamentoDTO(String pedidoId, String status, LocalDateTime dataCriacao) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
