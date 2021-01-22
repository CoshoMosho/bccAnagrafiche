package eu.winwinit.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eu.winwinit.bcc.entities.Ordini;

@Repository
public interface OrdiniRepository extends JpaRepository<Ordini, Integer> {

	List<Ordini> findAll();

	@Query(value = "select c " + "FROM Ordini c " + "WHERE cod_ordine =:codOrdine ")
	public Ordini findByCode(String codOrdine);
}