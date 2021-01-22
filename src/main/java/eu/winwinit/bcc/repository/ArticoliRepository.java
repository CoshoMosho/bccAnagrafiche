package eu.winwinit.bcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eu.winwinit.bcc.entities.Articoli;

public interface ArticoliRepository extends JpaRepository<Articoli, Integer> {

	List<Articoli> findAll();

	@Query(value = "select c " + "FROM Articoli c " + "WHERE cod_art =:codArt ")
	public Articoli findByCode(String codArt);

	@Query(value = "select c " + "FROM Articoli c " + "WHERE nomeProdotto LIKE %:nomeProdotto% ")
	public List<Articoli> findByNameLike(String nomeProdotto);

}
