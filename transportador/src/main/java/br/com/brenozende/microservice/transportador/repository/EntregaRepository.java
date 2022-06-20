package br.com.brenozende.microservice.transportador.repository;

import br.com.brenozende.microservice.transportador.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{

}
