package eu.winwinit.bcc.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "articoli")
public class Articoli implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private String codiceArticolo;
	@Column(unique = true, nullable = false)
	private String nomeProdotto;
	private String descrizione;

	@OneToMany(mappedBy = "ordini")
	private Set<DettagliOrdine> dettagliOrdine = new HashSet<>();

	public Articoli() {
	}

	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Set<DettagliOrdine> getDettagliOrdine() {
		return dettagliOrdine;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setDettagliOrdine(Set<DettagliOrdine> dettagliOrdine) {
		this.dettagliOrdine = dettagliOrdine;
	}

}
