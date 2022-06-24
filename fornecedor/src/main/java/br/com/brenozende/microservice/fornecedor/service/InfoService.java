package br.com.brenozende.microservice.fornecedor.service;

import br.com.brenozende.microservice.fornecedor.model.InfoFornecedor;
import br.com.brenozende.microservice.fornecedor.repository.InfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;
    public InfoFornecedor getInfoPorEstado(String estado) {
        log.info("Buscando informações do fornecedor de {}", estado);
        return infoRepository.findByEstado(estado);
    }
}
