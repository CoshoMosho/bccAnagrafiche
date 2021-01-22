package eu.winwinit.bcc.model;

import java.util.List;

public class OrdiniInsert {
	private String codOrdine;
	private List<ArticoliQuantità> articoliList;

	public OrdiniInsert() {
	}

	public OrdiniInsert(String codOrdine, List<ArticoliQuantità> articoliList) {
		super();
		this.codOrdine = codOrdine;
		this.articoliList = articoliList;
	}

	public String getCodOrdine() {
		return codOrdine;
	}

	public List<ArticoliQuantità> getArticoliList() {
		return articoliList;
	}

	public void setCodOrdine(String codOrdine) {
		this.codOrdine = codOrdine;
	}

	public void setArticoliList(List<ArticoliQuantità> articoliList) {
		this.articoliList = articoliList;
	}

}
