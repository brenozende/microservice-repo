package br.com.brenozende.microservice.loja.controller;

import br.com.brenozende.microservice.loja.dto.CompraDTO;
import br.com.brenozende.microservice.loja.model.Compra;
import br.com.brenozende.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    CompraService compraService;

    @GetMapping("/{id}")
    public Compra getById(@PathVariable("id") Long id) {
        return compraService.getById(id);
    }

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compra) {
        return compraService.realizaCompra(compra);
    }
}
