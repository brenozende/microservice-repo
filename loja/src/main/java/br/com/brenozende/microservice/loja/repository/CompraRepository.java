package br.com.brenozende.microservice.loja.repository;

import br.com.brenozende.microservice.loja.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}