package it.unipv.ingsw.lasout.model.vault;

import java.util.List;

import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;

public interface IVaultData {

	// metodo per settare un metodo di pagamento
	public void setPaymentMethods(List<PaymentMethod> methods);
	
    public List<PaymentMethod> getPaymentMethods();
    
    public void processPayment(PaymentMethod paymentMethod);
    
}
