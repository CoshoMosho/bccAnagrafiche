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
	@Column(name = "CODART", unique = true, nullable = false)
	private String cod_art;
	@Column(name = "NOMEPRODOTTO", unique = true, nullable = false)
	private String nome_prodotto;
	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@OneToMany(mappedBy = "cod_ordine_fk")
	private Set<DettagliOrdine> dettagli_ordine = new HashSet<>();

	public Articoli() {
	}

	public String getCodArt() {
		return cod_art;
	}

	public String getNomeProdotto() {
		return nome_prodotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setCodArt(String codArt) {
		this.cod_art = codArt;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nome_prodotto = nomeProdotto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Set<DettagliOrdine> getDettagliOrdine() {
		return dettagli_ordine;
	}

	public void setDettagliOrdine(Set<DettagliOrdine> dettagliOrdine) {
		this.dettagli_ordine = dettagliOrdine;
	}

}
