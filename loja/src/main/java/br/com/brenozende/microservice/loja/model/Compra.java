package br.com.brenozende.microservice.loja.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Compra {

    @Id
    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;
}
