package br.com.brenozende.microservice.transportador.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EntregaDTO {

	private Long pedidoId;
	
	private LocalDate dataParaEntrega;
	
	private String enderecoOrigem;
	
	private String enderecoDestino;


}
