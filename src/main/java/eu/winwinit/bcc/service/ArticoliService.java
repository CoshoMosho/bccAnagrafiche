package eu.winwinit.bcc.service;

import java.util.List;

import eu.winwinit.bcc.entities.Articoli;
import eu.winwinit.bcc.model.ArticoliRequests;

public interface ArticoliService {

	public List<ArticoliRequests> findAll();

	public ArticoliRequests findByCode(String codArt);

	public List<ArticoliRequests> findByNome(String nomeProdotto);

	public Articoli createArticolo(ArticoliRequests articoliRequests);

	public Articoli updateArticolo(ArticoliRequests articoliRequests);

	public Articoli deleteArticolo( String code);

}
