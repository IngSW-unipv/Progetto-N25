package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;

public class CurrentAccount implements PaymentMethod{
	private User utente;
    private String iban;
    
	public CurrentAccount(User utente, String iban) {
		this.utente = utente;
		this.iban = iban;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String getMethodName() {
		
		return "Conto corrente: " + iban;
		
	}

	@Override
	public boolean autorizza() {
		
		return true;
	}
}
