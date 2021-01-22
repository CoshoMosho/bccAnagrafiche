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
	@JoinColumn(name = "codiceArticolo")
	private Articoli articolo;
	@ManyToOne
	@JoinColumn(name = "codiceOrdine")
	private Ordini ordini;

	@Column(name = "quantità")
	private int quantità;

	public DettagliOrdine() {
	}

	public int getId() {
		return id;
	}

	public Articoli getArticolo() {
		return articolo;
	}

	public Ordini getOrdine() {
		return ordini;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setArticolo(Articoli articolo) {
		this.articolo = articolo;
	}

	public void setOrdine(Ordini ordine) {
		this.ordini = ordine;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

}
