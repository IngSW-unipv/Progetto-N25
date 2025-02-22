package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.util.List;

import it.unipv.ingsw.lasout.model.vault.Vault;

public interface PaymentMethod {
	
	boolean autorizza();
	
	String getMethodName();
	
	List<PaymentMethod> getAll (PaymentMethod paymentmethod) throws Exception;
	
	PaymentMethod get (PaymentMethod paymentmethod) throws Exception;
	
	void delete (PaymentMethod paymentmethod) throws Exception;
	
	void save (PaymentMethod paymentmethod) throws Exception;
	
	void saveInPaymentMethod (Vault v, PaymentMethod paymentmethod) throws Exception;
	
	void deleteInPaymentMethod(PaymentMethod p) throws Exception;
	
	void setVault(Vault vault);
	
	void getId(PaymentMethod p) throws Exception;

	void setId(int int1);
	
}
