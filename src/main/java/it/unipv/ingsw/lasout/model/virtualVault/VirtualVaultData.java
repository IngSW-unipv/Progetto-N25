package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.model.vault.IVaultData;
import it.unipv.ingsw.lasout.model.vault.PaymentMethod;

import java.util.List;

public class VirtualVaultData implements IVaultData{

    @Override
    public void setPaymentMethods(List<PaymentMethod> methods){

    }

    @Override
    public List<PaymentMethod> getPaymentMethods(){
        return List.of();
    }

    @Override
    public void processPayment(PaymentMethod paymentMethod) {

    }
}
