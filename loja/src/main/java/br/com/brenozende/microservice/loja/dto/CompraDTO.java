package br.com.brenozende.microservice.loja.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompraDTO {

    private List<ItemDaCompraDTO> itens;
    private EnderecoDTO endereco;
}
