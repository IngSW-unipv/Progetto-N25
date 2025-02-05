package it.unipv.ingsw.lasout.model.vault;

public class PayPal implements PaymentMethod{

	private String numeroCarta;

	public PayPal(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	@Override
	public String getMethodName() {		
		return "PayPal: ";
	}
	
	@Override
	public boolean autorizza() {
	// TODO Auto-generated method stub
		return true;
	}  
}
