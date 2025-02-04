package it.unipv.ingsw.lasout.model.vault;

public class PayPal implements PaymentMethod{
private RegisteredUser utente;
    
	public PayPal(RegisteredUser utente) {
		this.utente = utente;
	}

	public RegisteredUser getUtente() {
		return utente;
	}

	public void setUtente(RegisteredUser utente) {
		this.utente = utente;
	}  
}
