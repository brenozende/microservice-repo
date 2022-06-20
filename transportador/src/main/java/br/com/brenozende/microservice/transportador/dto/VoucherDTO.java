package br.com.brenozende.microservice.transportador.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VoucherDTO {

	private Long numero;
	
	private LocalDate previsaoParaEntrega;

}
