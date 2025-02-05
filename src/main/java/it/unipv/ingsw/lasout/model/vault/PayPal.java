package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;

public class PayPal implements PaymentMethod{
private User utente;
    
	public PayPal(User utente) {
		this.utente = utente;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
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
