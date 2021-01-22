package eu.winwinit.bcc.service;

import java.util.List;

import eu.winwinit.bcc.entities.Ordini;
import eu.winwinit.bcc.model.OrdiniInsert;
import eu.winwinit.bcc.model.Ordinihandler;

public interface OrdiniService {

	public List<Ordinihandler> findAll();

	public Ordinihandler findByCode(String code);

	public Ordini creaOrdine(OrdiniInsert ordine);

	public Ordini aggiornaOrdine(Ordinihandler ordineReq);

	public String eliminaOrdine(String code);

}
