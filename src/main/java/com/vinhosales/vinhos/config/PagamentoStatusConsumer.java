package com.vinhosales.vinhos.config;

import com.vinhosales.vinhos.dto.PagamentoDTO;
import com.vinhosales.vinhos.repository.PedidoRepository;
import com.vinhosales.vinhos.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PagamentoStatusConsumer {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PagamentoStatusConsumer(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = "pagamento.queue")
    public void consumePaymentStatus(PagamentoDTO pagamentoDTO) {
        if ("DONE".equals(pagamentoDTO.getStatus())) {
            pedidoRepository.findById(pagamentoDTO.getPedidoId())
                    .flatMap(pedido -> {
                        return Flux.fromIterable(pedido.getCarrinho().getProdutos())
                                .flatMap(produto -> produtoRepository.findById(produto.getId())
                                        .flatMap(produtoDb -> {
                                            produtoDb.setQuantidade(produtoDb.getQuantidade() - produto.getQuantidade());
                                            return produtoRepository.save(produtoDb);
                                        })
                                )
                                .then(Mono.just(pedido));
                    })
                    .subscribe();
        }
    }
}

