package it.unipv.ingsw.lasout.model.vault.paymentmethod;

import java.util.List;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.vault.Vault;

public interface IPaymentMethodDAO extends IDao<PaymentMethod>{

	List<PaymentMethod> getAllPaymentMethod(Vault v) throws Exception;
}

