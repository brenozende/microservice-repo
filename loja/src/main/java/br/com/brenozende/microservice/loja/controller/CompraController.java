package br.com.brenozende.microservice.loja.controller;

import br.com.brenozende.microservice.loja.dto.CompraDTO;
import br.com.brenozende.microservice.loja.model.Compra;
import br.com.brenozende.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    CompraService compraService;

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compra) {
        return compraService.realizaCompra(compra);
    }
}
