package it.unipv.ingsw.lasout.model.vault;

import java.util.ArrayList;
import java.util.List;

public class VaultData implements IVaultData{
	
	private List<PaymentMethod> paymentMethods;


    public VaultData(){
        this.paymentMethods = new ArrayList<>();
    }

	@Override
	public void setPaymentMethods(List<PaymentMethod> methods) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PaymentMethod> getPaymentMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processPayment(PaymentMethod paymentMethod) {
		// TODO Auto-generated method stub
		
	}

}
