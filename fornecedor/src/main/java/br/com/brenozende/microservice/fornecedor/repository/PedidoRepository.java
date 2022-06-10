package br.com.brenozende.microservice.fornecedor.repository;

import br.com.brenozende.microservice.fornecedor.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}