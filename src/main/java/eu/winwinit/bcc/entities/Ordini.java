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
	@Column(unique = true, nullable = false)
	private String codiceOrdine;

	@Temporal(TemporalType.DATE)
	private Date dataOrdine;

	@OneToMany(mappedBy = "articolo")
	private Set<DettagliOrdine> dettagliOrdine = new HashSet<>();

	@PrePersist
	protected void onCreate() {
		this.dataOrdine = new Date();
	}

	public Ordini() {
	}

	public String getCodiceOrdine() {
		return codiceOrdine;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public Set<DettagliOrdine> getDettagliOrdine() {
		return dettagliOrdine;
	}

	public void setCodiceOrdine(String codiceOrdine) {
		this.codiceOrdine = codiceOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public void setDettagliOrdine(Set<DettagliOrdine> dettagliOrdine) {
		this.dettagliOrdine = dettagliOrdine;
	}

}
