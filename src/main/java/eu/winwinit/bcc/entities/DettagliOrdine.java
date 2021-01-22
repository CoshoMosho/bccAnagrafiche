package eu.winwinit.bcc.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dettagliordine")
public class DettagliOrdine implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "CODART")
	private Articoli cod_art_fk;
	@ManyToOne
	@JoinColumn(name = "CODORDINE")
	private Ordini cod_ordine_fk;

	@Column(name = "quantità")
	private int quantità;

	public DettagliOrdine() {
	}

	public int getId() {
		return id;
	}

	public Articoli getCodArt() {
		return cod_art_fk;
	}

	public Ordini getCodOrdine() {
		return cod_ordine_fk;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCodArt(Articoli codArt) {
		this.cod_art_fk = codArt;
	}

	public void setCodOrdine(Ordini codOrdine) {
		this.cod_ordine_fk = codOrdine;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

}
