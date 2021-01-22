package eu.winwinit.bcc.model;

public class ArticoliRequests {

	private String codArt;
	private String nomeProdotto;
	private String descrizione;

	public ArticoliRequests() {
	}

	public ArticoliRequests(String codArt, String nome_prodotto, String descrizione) {
		super();
		this.codArt = codArt;
		this.nomeProdotto = nome_prodotto;
		this.descrizione = descrizione;
	}

	public String getCodArt() {
		return codArt;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setCodArt(String codArt) {
		this.codArt = codArt;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
