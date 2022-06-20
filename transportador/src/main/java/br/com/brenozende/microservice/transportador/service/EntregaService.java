package br.com.brenozende.microservice.transportador.service;

import br.com.brenozende.microservice.transportador.dto.EntregaDTO;
import br.com.brenozende.microservice.transportador.model.Entrega;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brenozende.microservice.transportador.dto.VoucherDTO;
import br.com.brenozende.microservice.transportador.repository.EntregaRepository;

@Service
@Slf4j
public class EntregaService {
	
	@Autowired
	private EntregaRepository repository;

	public VoucherDTO reservaEntrega(EntregaDTO pedidoDTO) {
		log.info("Realizando reserva");
		
		Entrega entrega = new Entrega();
		entrega.setDataParaBusca(pedidoDTO.getDataParaEntrega());
		entrega.setPrevisaoParaEntrega(pedidoDTO.getDataParaEntrega().plusDays(1L));
		entrega.setEnderecoDestino(pedidoDTO.getEnderecoDestino());
		entrega.setEnderecoOrigem(pedidoDTO.getEnderecoOrigem());
		entrega.setPedidoId(pedidoDTO.getPedidoId());
		
		repository.save(entrega);
		
		VoucherDTO voucher = new VoucherDTO();
		voucher.setNumero(entrega.getId());
		voucher.setPrevisaoParaEntrega(entrega.getPrevisaoParaEntrega());
		return voucher;
	}

}
