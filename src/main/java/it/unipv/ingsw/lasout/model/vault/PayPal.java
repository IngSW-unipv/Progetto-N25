package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;

public class PayPal implements PaymentMethod{

	private User utente;
	private String numeroCarta;

	public PayPal(User utente, String numeroCarta) {
		this.utente = utente;
		this.numeroCarta = numeroCarta;
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

	@Override
	public String getMethodName() {		
		return "PayPal: " + utente;
	}
	
	@Override
	public boolean autorizza() {
	// TODO Auto-generated method stub
		return true;
	}  
}
