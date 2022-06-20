package br.com.brenozende.microservice.transportador.controller;

import br.com.brenozende.microservice.transportador.dto.EntregaDTO;
import br.com.brenozende.microservice.transportador.dto.VoucherDTO;
import br.com.brenozende.microservice.transportador.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrega")
public class EntregaController {
	
	@Autowired
	private EntregaService entregaService;

	@PostMapping
	public VoucherDTO reservaEntrega(@RequestBody EntregaDTO pedidoDTO) {
		return entregaService.reservaEntrega(pedidoDTO);
	}
}
