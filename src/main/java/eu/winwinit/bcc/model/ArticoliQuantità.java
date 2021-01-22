package eu.winwinit.bcc.model;

public class ArticoliQuantità {
	private String codArt;
	private int quantità;

	public ArticoliQuantità() {
	}

	public ArticoliQuantità(String codArt, int quantità) {
		super();
		this.codArt = codArt;
		this.quantità = quantità;
	}

	public String getCodArt() {
		return codArt;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setCodArt(String codArt) {
		this.codArt = codArt;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
}
