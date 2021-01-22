package eu.winwinit.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.winwinit.bcc.entities.DettagliOrdine;
import eu.winwinit.bcc.entities.Ordini;

@Repository
public interface DettagliOrdineRepository extends JpaRepository<DettagliOrdine, Integer> {

	List<DettagliOrdine> findAll();

	public List<DettagliOrdine> findByOrdini(Ordini ordini);

}
