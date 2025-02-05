package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;

public class CreditCard implements PaymentMethod{
	private User utente;
    private String numeroCarta;
    private String dataScadenza;
    private String cvv;
    
	public CreditCard(User utente, String numeroCarta, String dataScadenza, String cvv) {
		this.utente = utente;
		this.numeroCarta = numeroCarta;
		this.dataScadenza = dataScadenza;
		this.cvv = cvv;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
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

	@Override
	public String getMethodName() {
		
		return "Carta di credito: " + numeroCarta;
	}

	@Override
	public boolean autorizza() {
		// TODO Auto-generated method stub
		return true;
	}
}
