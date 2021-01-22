package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articoli;
import eu.winwinit.bcc.model.ArticoliRequests;
import eu.winwinit.bcc.repository.ArticoliRepository;

@Service
public class ArticoliServiceImpl implements ArticoliService {

	@Autowired
	ArticoliRepository articoliRepository;

	@Override
	public List<ArticoliRequests> findAll() {
		List<ArticoliRequests> list = new ArrayList<ArticoliRequests>();
		for (Articoli articoli : articoliRepository.findAll()) {
			ArticoliRequests articoloreq = new ArticoliRequests();
			articoloreq.setCodArt(articoli.getCodiceArticolo());
			articoloreq.setNomeProdotto(articoli.getNomeProdotto());
			articoloreq.setDescrizione(articoli.getDescrizione());
			list.add(articoloreq);
		}
		return list;
	}

	@Override
	public ArticoliRequests findByCode(String codArt) {
		Articoli articolo = articoliRepository.findByCodiceArticolo(codArt);
		if (articolo == null) {
			return null;
		}
		ArticoliRequests articoloreq = new ArticoliRequests();
		articoloreq.setCodArt(codArt);
		articoloreq.setNomeProdotto(articolo.getNomeProdotto());
		articoloreq.setDescrizione(articolo.getDescrizione());
		return articoloreq;
	}

	@Override
	public List<ArticoliRequests> findByNome(String nomeProdotto) {
		List<ArticoliRequests> list = new ArrayList<ArticoliRequests>();
		for (Articoli articoli : articoliRepository.findByNameLike(nomeProdotto)) {
			if (articoli == null) {
				continue;
			}
			ArticoliRequests articoloreq = new ArticoliRequests();
			articoloreq.setCodArt(articoli.getCodiceArticolo());
			articoloreq.setNomeProdotto(articoli.getNomeProdotto());
			articoloreq.setDescrizione(articoli.getDescrizione());
			list.add(articoloreq);
		}
		return list;
	}

	@Override
	public Articoli createArticolo(ArticoliRequests articoliRequests) {
		if (articoliRepository.findByCodiceArticolo(articoliRequests.getCodArt()) != null || articoliRequests.getNomeProdotto()==null) {
			return null;
		}
		Articoli articolo = new Articoli();
		articolo.setCodiceArticolo(articoliRequests.getCodArt());
		articolo.setDescrizione(articoliRequests.getDescrizione());
		articolo.setNomeProdotto(articoliRequests.getNomeProdotto());
		return articoliRepository.save(articolo);
	}

	@Override
	public Articoli updateArticolo(ArticoliRequests articoliRequests) {
		Articoli articoloEsistente = articoliRepository.findByCodiceArticolo(articoliRequests.getCodArt());
		if (articoloEsistente == null || articoliRequests.getNomeProdotto()==null) {
			return null;
		}
		Articoli articolo = new Articoli();
		articolo.setCodiceArticolo(articoloEsistente.getCodiceArticolo());
		articolo.setDescrizione(articoliRequests.getDescrizione());
		articolo.setNomeProdotto(articoliRequests.getNomeProdotto());
		
		return articoliRepository.save(articolo);
	}

	@Override
	public Articoli deleteArticolo(String code) {
		Articoli articolo = articoliRepository.findByCodiceArticolo(code);
		if (articolo == null) {
			return null;
		}
		articoliRepository.delete(articolo);
		return articolo;
	}
	
}
