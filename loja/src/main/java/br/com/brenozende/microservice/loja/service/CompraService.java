package br.com.brenozende.microservice.loja.service;

import br.com.brenozende.microservice.loja.client.FornecedorClient;
import br.com.brenozende.microservice.loja.client.TransportadorClient;
import br.com.brenozende.microservice.loja.dto.*;
import br.com.brenozende.microservice.loja.enums.CompraStatus;
import br.com.brenozende.microservice.loja.model.Compra;
import br.com.brenozende.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class CompraService {

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private TransportadorClient transportadorClient;

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

        Compra compraSalva = new Compra();
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraSalva.setStatus(CompraStatus.RECEBIDO);
        compraRepository.save(compraSalva);
        compra.setCompraId(compraSalva.getId());

        String estado = compra.getEndereco().getEstado();
        InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);
        if (info == null) {
            log.info("Não foi encontrado fornecedor para essa região");
            return null;
        }
        log.info("Endereço do fornecedor de {}: {}", estado,info.getEndereco());

        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setStatus(CompraStatus.REALIZADO);
        compraRepository.save(compraSalva);

//        if(true)
//            throw new RuntimeException();

        InfoEntregaDTO entregaDTO = new InfoEntregaDTO();
        entregaDTO.setPedidoId(pedido.getId());
        entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDTO.setEnderecoOrigem(info.getEndereco());
        entregaDTO.setEnderecoDestino(compra.getEndereco().toString());
        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);
        compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
        compraSalva.setVoucher(voucher.getNumero());
        compraSalva.setStatus(CompraStatus.ENTREGA_RESERVADA);
        compraRepository.save(compraSalva);

        return compraSalva;
    }

    public Compra reprocessaCompra(Long id){
        return null;
    }

    public Compra cancelaCompra(Long id){
        return null;
    }

    private Compra realizaCompraFallback(CompraDTO compraDTO) {
        if (compraDTO.getCompraId() != null){
            Optional<Compra> compraOptional = compraRepository.findById(compraDTO.getCompraId());
            if (compraOptional.isPresent()) {
                return compraOptional.get();
            }
        }
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
        return compraFallback;
    }

}
