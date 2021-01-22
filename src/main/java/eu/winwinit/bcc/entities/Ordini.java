package eu.winwinit.bcc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ordini")
public class Ordini implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODORDINE", unique = true, nullable = false)
	private String cod_ordine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAORDINE")
	private Date data_ordine;

	@OneToMany(mappedBy = "cod_art_fk")
	private Set<DettagliOrdine> dettagli_ordine = new HashSet<>();

	@PrePersist
	protected void onCreate() {
		this.data_ordine = new Date();
	}

	public Ordini() {
	}

	public String getCodOrdine() {
		return cod_ordine;
	}

	public Date getDataOrdine() {
		return data_ordine;
	}

	public void setCodOrdine(String codOrdine) {
		this.cod_ordine = codOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.data_ordine = dataOrdine;
	}

	public Set<DettagliOrdine> getDettagliOrdine() {
		return dettagli_ordine;
	}

	public void setDettagliOrdine(Set<DettagliOrdine> dettagliOrdine) {
		this.dettagli_ordine = dettagliOrdine;
	}

}
