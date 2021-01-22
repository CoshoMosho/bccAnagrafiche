package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articoli;
import eu.winwinit.bcc.entities.DettagliOrdine;
import eu.winwinit.bcc.entities.Ordini;
import eu.winwinit.bcc.model.ArticoliQuantità;
import eu.winwinit.bcc.model.OrdiniInsert;
import eu.winwinit.bcc.model.Ordinihandler;
import eu.winwinit.bcc.repository.ArticoliRepository;
import eu.winwinit.bcc.repository.DettagliOrdiniRepository;
import eu.winwinit.bcc.repository.OrdiniRepository;

@Service
public class OrdiniServiceImpl implements OrdiniService {

	@Autowired
	ArticoliRepository articoliRepository;

	@Autowired
	OrdiniRepository ordiniRepository;

	@Autowired
	DettagliOrdiniRepository dettagliOrdiniRepository;

	@Override
	public List<Ordinihandler> findAll() {
		HashMap<String, Ordinihandler> ordini = new HashMap<String, Ordinihandler>();
		List<DettagliOrdine> dettagliOrdini = dettagliOrdiniRepository.findAll();
		if (dettagliOrdini == null) {
			return null;
		}
		for (DettagliOrdine dettagliOrdine : dettagliOrdini) {
			ArticoliQuantità artquant = new ArticoliQuantità(dettagliOrdine.getCodArt().getCodArt(),
					dettagliOrdine.getQuantità());
			if (ordini.containsKey(dettagliOrdine.getCodOrdine().getCodOrdine())) {
				ordini.get(dettagliOrdine.getCodOrdine().getCodOrdine()).getArticoliList().add(artquant);
			} else {
				Ordinihandler ordine = new Ordinihandler();
				ordine.setCod_ordine(dettagliOrdine.getCodOrdine().getCodOrdine());
				ordine.setData_ordine(dettagliOrdine.getCodOrdine().getDataOrdine());
				ordine.setArticoliList(new ArrayList<ArticoliQuantità>());
				ordine.getArticoliList().add(artquant);
				ordini.put(dettagliOrdine.getCodOrdine().getCodOrdine(), ordine);
			}
		}
		return new ArrayList<Ordinihandler>(ordini.values());
	}

	@Override
	public Ordinihandler findByCode(String code) {
		Ordini ordini = ordiniRepository.findByCode(code);
		if (ordini == null) {
			return null;
		}
		List<DettagliOrdine> dettagliOrdini = dettagliOrdiniRepository.findByCode(ordini);
		if (dettagliOrdini == null) {
			return null;
		}
		Ordinihandler ordine = new Ordinihandler();
		ordine.setCod_ordine(dettagliOrdini.get(0).getCodOrdine().getCodOrdine());
		ordine.setData_ordine(dettagliOrdini.get(0).getCodOrdine().getDataOrdine());
		ordine.setArticoliList(new ArrayList<ArticoliQuantità>());
		for (DettagliOrdine dettagliOrdine : dettagliOrdini) {
			ArticoliQuantità artquant = new ArticoliQuantità(dettagliOrdine.getCodArt().getCodArt(),
					dettagliOrdine.getQuantità());
			ordine.getArticoliList().add(artquant);
		}
		return ordine;
	}

	@Override
	public Ordini creaOrdine(OrdiniInsert ordineReq) {
		Ordini ordine = new Ordini();
		ordine.setCodOrdine(ordineReq.getCod_ordine());
		// controllare @prepersist e questione date
		ordine.setDataOrdine(new Date());
		if (ordiniRepository.findByCode(ordineReq.getCod_ordine()) != null) {
			return null;
		}
		for (ArticoliQuantità articoloQuantità : ordineReq.getArticoliList()) {
			Articoli articolo = articoliRepository.findByCode(articoloQuantità.getCodArt());
			if (articolo == null) {
				continue;
			}
			DettagliOrdine dettagliOrdine = new DettagliOrdine();
			dettagliOrdine.setCodOrdine(ordine);
			dettagliOrdine.setCodArt(articolo);
			dettagliOrdine.setQuantità(articoloQuantità.getQuantità());
			ordiniRepository.save(ordine);
			dettagliOrdiniRepository.save(dettagliOrdine);
		}
		return ordine;
	}

	@Override
	public Ordini aggiornaOrdine(Ordinihandler ordineReq) {
		Ordini ordineDaModificare = ordiniRepository.findByCode(ordineReq.getCod_ordine());
		if (ordineDaModificare == null) {
			return null;
		}
		Ordini ordine = new Ordini();
		ordine.setCodOrdine(ordineReq.getCod_ordine());
		ordine.setDataOrdine(new Date());
		List<DettagliOrdine> listadettagli = new ArrayList<DettagliOrdine>();
		for (ArticoliQuantità articoloQuantità : ordineReq.getArticoliList()) {
			Articoli articolo = articoliRepository.findByCode(articoloQuantità.getCodArt());
			if (articolo == null) {
				continue;
			}
			DettagliOrdine dettagliOrdine = new DettagliOrdine();
			dettagliOrdine.setCodOrdine(ordine);
			dettagliOrdine.setCodArt(articolo);
			dettagliOrdine.setQuantità(articoloQuantità.getQuantità());
			listadettagli.add(dettagliOrdine);

		}
		ordiniRepository.delete(ordineDaModificare);
		ordiniRepository.save(ordine);
		for (DettagliOrdine dettagliOrdine : listadettagli) {
			dettagliOrdiniRepository.save(dettagliOrdine);
		}
		return ordine;
	}

	@Override
	public String eliminaOrdine(String code) {
		Ordini ordine = ordiniRepository.findByCode(code);
		if (ordine == null) {
			return null;
		}
		ordiniRepository.delete(ordine);
		return code;
	}

}
