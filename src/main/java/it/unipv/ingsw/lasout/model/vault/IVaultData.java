package it.unipv.ingsw.lasout.model.vault;

import java.util.List;

public interface IVaultData {

	// metodo per settare un metodo di pagamento
	public void setPaymentMethods(List<PaymentMethod> methods);
	
    public List<PaymentMethod> getPaymentMethods();
    
    public void processPayment(PaymentMethod paymentMethod);
    
}
