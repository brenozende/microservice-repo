package br.com.brenozende.microservice.loja.model;

import br.com.brenozende.microservice.loja.enums.CompraStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;

    private LocalDate dataParaEntrega;

    private Long voucher;

    @Enumerated(EnumType.STRING)
    private CompraStatus status;
}
