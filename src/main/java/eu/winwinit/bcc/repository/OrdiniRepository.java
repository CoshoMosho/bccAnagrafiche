package eu.winwinit.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.winwinit.bcc.entities.Ordini;

@Repository
public interface OrdiniRepository extends JpaRepository<Ordini, Integer> {

	List<Ordini> findAll();

	public Ordini findByCodiceOrdine(String codiceOrdine);
}