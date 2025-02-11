package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.util.List;

import it.unipv.ingsw.lasout.model.vault.Vault;

public class CurrentAccount implements PaymentMethod{
    private String iban;
    
	public CurrentAccount(String iban) {
		this.iban = iban;
	}
	
	public CurrentAccount() {
		
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
	
	@Override
	public void delete(PaymentMethod paymentmethod) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(PaymentMethod paymentmethod) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVault(Vault vault) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaymentMethod> getAll(PaymentMethod paymentmethod) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentMethod get(PaymentMethod paymentmethod) throws Exception {
		
		return paymentmethod;
		
	}
}
