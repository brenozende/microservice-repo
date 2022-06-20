package br.com.brenozende.microservice.loja.client;

import br.com.brenozende.microservice.loja.dto.InfoEntregaDTO;
import br.com.brenozende.microservice.loja.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("transportador")
public interface TransportadorClient {

    @PostMapping(path = "/entrega")
    VoucherDTO reservaEntrega(InfoEntregaDTO entregaDTO);
}
