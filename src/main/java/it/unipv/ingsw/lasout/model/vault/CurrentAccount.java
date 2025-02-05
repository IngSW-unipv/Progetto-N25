package it.unipv.ingsw.lasout.model.vault;

public class CurrentAccount implements PaymentMethod{
    private String iban;
    
	public CurrentAccount(String iban) {
		this.iban = iban;
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
