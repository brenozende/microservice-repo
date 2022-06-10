package br.com.brenozende.microservice.fornecedor.controller;

import br.com.brenozende.microservice.fornecedor.model.InfoFornecedor;
import br.com.brenozende.microservice.fornecedor.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
@Slf4j
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/{estado}")
    public InfoFornecedor getInfoPorEstado(@PathVariable String estado) {
        log.info("Recebido pedido de informações do fornecedor de {}", estado);
        return infoService.getInfoPorEstado(estado);
    }
}
