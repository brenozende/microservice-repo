package br.com.brenozende.microservice.fornecedor.service;

import br.com.brenozende.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.brenozende.microservice.fornecedor.model.Pedido;
import br.com.brenozende.microservice.fornecedor.model.PedidoItem;
import br.com.brenozende.microservice.fornecedor.model.Produto;
import br.com.brenozende.microservice.fornecedor.repository.PedidoRepository;
import br.com.brenozende.microservice.fornecedor.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido realizaPedido(List<ItemDoPedidoDTO> itens) {
        log.info("Realizando um pedido");

        if(itens == null) {
            return null;
        }

        List<PedidoItem> pedidoItens = toPedidoItem(itens);
        Pedido pedido = new Pedido(pedidoItens);
        pedido.setTempoDePreparo(itens.size());
        return pedidoRepository.save(pedido);
    }

    public Pedido getPedidoPorId(Long id) {
        return this.pedidoRepository.findById(id).orElse(new Pedido());
    }

    private List<PedidoItem> toPedidoItem(List<ItemDoPedidoDTO> itens) {

        List<Long> idsProdutos = itens
                .stream()
                .map(ItemDoPedidoDTO::getId)
                .collect(Collectors.toList());

        List<Produto> produtosDoPedido = produtoRepository.findByIdIn(idsProdutos);

        List<PedidoItem> pedidoItens = itens
                .stream()
                .map(item -> {
                    Produto produto = produtosDoPedido
                            .stream()
                            .filter(p -> p.getId() == item.getId())
                            .findFirst().get();

                    PedidoItem pedidoItem = new PedidoItem();
                    pedidoItem.setProduto(produto);
                    pedidoItem.setQuantidade(item.getQuantidade());
                    return pedidoItem;
                })
                .collect(Collectors.toList());
        return pedidoItens;
    }
}

