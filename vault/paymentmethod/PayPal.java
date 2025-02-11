package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.util.List;

import it.unipv.ingsw.lasout.model.vault.Vault;

public class PayPal implements PaymentMethod{

	private String numeroCarta;

	public PayPal(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	
	public PayPal() {
		
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	

	@Override
	public String getMethodName() {		
		return "PayPal";
	}
	
	@Override
	public boolean autorizza() {
	// TODO Auto-generated method stub
		return true;
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

	
}
