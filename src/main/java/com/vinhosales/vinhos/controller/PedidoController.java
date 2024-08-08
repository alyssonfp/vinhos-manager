package com.vinhosales.vinhos.controller;

import com.vinhosales.vinhos.model.Pedido;
import com.vinhosales.vinhos.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Mono<Pedido> createPedido(@RequestBody Pedido pedido) {
        return pedidoService.createPedido(pedido);
    }

    @GetMapping("/carrinho/{carrinhoId}")
    public Mono<Pedido> getPedidoByCarrinhoId(@PathVariable String carrinhoId) {
        return pedidoService.getPedidoByCarrinhoId(carrinhoId);
    }
}
