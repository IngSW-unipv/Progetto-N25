package it.unipv.ingsw.lasout.model.vault;

public class CurrentAccount implements PaymentMethod{
	private RegisteredUser utente;
    private String iban;
    
	public CurrentAccount(RegisteredUser utente, String iban) {
		this.utente = utente;
		this.iban = iban;
	}

	public RegisteredUser getUtente() {
		return utente;
	}

	public void setUtente(RegisteredUser utente) {
		this.utente = utente;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
}
