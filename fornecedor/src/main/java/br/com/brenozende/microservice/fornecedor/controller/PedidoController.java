package br.com.brenozende.microservice.fornecedor.controller;

import br.com.brenozende.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.brenozende.microservice.fornecedor.model.Pedido;
import br.com.brenozende.microservice.fornecedor.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido")
@Slf4j
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido realizaPedido(@RequestBody List<ItemDoPedidoDTO> produtos) {
        log.info("Pedido recebido");
        return pedidoService.realizaPedido(produtos);
    }

    @RequestMapping("/{id}")
    public Pedido getPedidoPorId(@PathVariable Long id) {
        return pedidoService.getPedidoPorId(id);
    }
}

