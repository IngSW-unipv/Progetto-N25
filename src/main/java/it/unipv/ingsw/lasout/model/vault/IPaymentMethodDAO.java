package it.unipv.ingsw.lasout.model.vault;

public interface IPaymentMethodDAO {
	
	void load (PaymentMethod paymentmethod) throws Exception;
	
	void delete (PaymentMethod paymentmethod) throws Exception;

	void save (PaymentMethod paymentmethod) throws Exception;
}
