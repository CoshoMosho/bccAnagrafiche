package eu.winwinit.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eu.winwinit.bcc.entities.Articoli;
import eu.winwinit.bcc.entities.DettagliOrdine;
import eu.winwinit.bcc.entities.Ordini;

@Repository
public interface DettagliOrdiniRepository extends JpaRepository<DettagliOrdine, Integer> {

	List<DettagliOrdine> findAll();

	@Query(value = "select c " + "FROM DettagliOrdine c " + "WHERE cod_ordine_fk =:Ordine ")
	public List<DettagliOrdine> findByCode(Ordini Ordine);
	
	@Query(value = "select c " + "FROM DettagliOrdine c " + "WHERE cod_art_fk =:articolo ")
	public List<DettagliOrdine> findByCodeArticolo(Articoli articolo);
}
