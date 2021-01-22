package eu.winwinit.bcc.model;

import java.util.Date;
import java.util.List;

public class Ordinihandler {
	private String codOrdine;
	private Date dataOrdine;
	private List<ArticoliQuantità> articoliList;

	public Ordinihandler() {
	}

	public Ordinihandler(String codOrdine, Date dataOrdine, List<ArticoliQuantità> articoliList) {
		super();
		this.codOrdine = codOrdine;
		this.dataOrdine = dataOrdine;
		this.articoliList = articoliList;
	}

	public String getCodOrdine() {
		return codOrdine;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public List<ArticoliQuantità> getArticoliList() {
		return articoliList;
	}

	public void setCodOrdine(String codOrdine) {
		this.codOrdine = codOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public void setArticoliList(List<ArticoliQuantità> articoliList) {
		this.articoliList = articoliList;
	}

}
