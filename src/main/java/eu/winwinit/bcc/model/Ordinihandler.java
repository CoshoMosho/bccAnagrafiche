package eu.winwinit.bcc.model;

import java.util.Date;
import java.util.List;

public class Ordinihandler {
	private String cod_ordine;
	private Date data_ordine;
	private List<ArticoliQuantità> articoliList;

	public Ordinihandler() {
	}

	public Ordinihandler(String cod_ordine, Date data_ordine, List<ArticoliQuantità> articoliList) {
		super();
		this.cod_ordine = cod_ordine;
		this.data_ordine = data_ordine;
		this.articoliList = articoliList;
	}

	public String getCod_ordine() {
		return cod_ordine;
	}

	public Date getData_ordine() {
		return data_ordine;
	}

	public List<ArticoliQuantità> getArticoliList() {
		return articoliList;
	}

	public void setCod_ordine(String cod_ordine) {
		this.cod_ordine = cod_ordine;
	}

	public void setData_ordine(Date data_ordine) {
		this.data_ordine = data_ordine;
	}

	public void setArticoliList(List<ArticoliQuantità> articoliList) {
		this.articoliList = articoliList;
	}

}
