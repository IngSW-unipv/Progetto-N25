package it.unipv.ingsw.lasout.vault;

public class CreditCard implements PaymentMethod{

	private RegisteredUser utente;
    private String numeroCarta;
    private String dataScadenza;
    private String cvv;
    
	public CreditCard(RegisteredUser utente, String numeroCarta, String dataScadenza, String cvv) {
		this.utente = utente;
		this.numeroCarta = numeroCarta;
		this.dataScadenza = dataScadenza;
		this.cvv = cvv;
	}

	public RegisteredUser getUtente() {
		return utente;
	}

	public void setUtente(RegisteredUser utente) {
		this.utente = utente;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
}
