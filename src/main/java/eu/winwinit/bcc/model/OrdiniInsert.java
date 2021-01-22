package eu.winwinit.bcc.model;

import java.util.List;

public class OrdiniInsert {
	private String cod_ordine;
	private List<ArticoliQuantità> articoliList;

	public OrdiniInsert() {
	}

	public OrdiniInsert(String cod_ordine, List<ArticoliQuantità> articoliList) {
		super();
		this.cod_ordine = cod_ordine;
		this.articoliList = articoliList;
	}

	public String getCod_ordine() {
		return cod_ordine;
	}

	public List<ArticoliQuantità> getArticoliList() {
		return articoliList;
	}

	public void setCod_ordine(String cod_ordine) {
		this.cod_ordine = cod_ordine;
	}

	public void setArticoliList(List<ArticoliQuantità> articoliList) {
		this.articoliList = articoliList;
	}

}
