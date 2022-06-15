package br.com.brenozende.microservice.loja.service;

import br.com.brenozende.microservice.loja.client.FornecedorClient;
import br.com.brenozende.microservice.loja.dto.CompraDTO;
import br.com.brenozende.microservice.loja.dto.InfoFornecedorDTO;
import br.com.brenozende.microservice.loja.dto.InfoPedidoDTO;
import br.com.brenozende.microservice.loja.model.Compra;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import br.com.brenozende.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class CompraService {

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private CompraRepository compraRepository;

    @HystrixCommand
    public Compra getById(Long id) {
        log.info("Buscando informações de compra");
        Optional<Compra> result = compraRepository.findById(id);
        return result.orElse(null);
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallback")
    public Compra realizaCompra(CompraDTO compra) {

        String estado = compra.getEndereco().getEstado();
        log.info("Buscando informações do fornecedor de {}", estado);
        InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);
        if (info != null)
            log.info("Endereço do fornecedor de {}: {}", estado,info.getEndereco());
        else
            log.info("Não foi encontrado fornecedor para essa região");

        log.info("Realizando um pedido");
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());



        Compra compraSalva = new Compra();
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraRepository.save(compraSalva);


        return compraSalva;
    }

    private Compra realizaCompraFallback(CompraDTO compraDTO) {
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
        return compraFallback;
    }

}
