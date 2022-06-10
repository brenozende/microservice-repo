package br.com.brenozende.microservice.loja.client;

import br.com.brenozende.microservice.loja.dto.InfoFornecedorDTO;
import br.com.brenozende.microservice.loja.dto.InfoPedidoDTO;
import br.com.brenozende.microservice.loja.dto.ItemDaCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @RequestMapping("/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

    @PostMapping("/pedido")
    InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
}
