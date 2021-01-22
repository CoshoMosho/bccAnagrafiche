package eu.winwinit.bcc.model;

//serializable??
public class ArticoliRequests {

	private String cod_art;
	private String nome_prodotto;
	private String descrizione;

	public ArticoliRequests() {
	}

	public ArticoliRequests(String cod_art, String nome_prodotto, String descrizione) {
		super();
		this.cod_art = cod_art;
		this.nome_prodotto = nome_prodotto;
		this.descrizione = descrizione;
	}

	public String getCod_art() {
		return cod_art;
	}

	public String getNome_prodotto() {
		return nome_prodotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setCod_art(String cod_art) {
		this.cod_art = cod_art;
	}

	public void setNome_prodotto(String nome_prodotto) {
		this.nome_prodotto = nome_prodotto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
