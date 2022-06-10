package br.com.brenozende.microservice.fornecedor.repository;

import br.com.brenozende.microservice.fornecedor.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByEstado(String estado);

    List<Produto> findByIdIn(List<Long> ids);
}