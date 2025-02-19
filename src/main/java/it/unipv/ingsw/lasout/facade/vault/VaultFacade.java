package it.unipv.ingsw.lasout.facade.vault;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;

public interface VaultFacade {
	
	boolean sendMoney(User Sender, User Receiver);
	
	boolean newVaultinVirtualVault(Vault vault, User user);
	
	boolean newVaultinVault(Vault vault);
	
	boolean getVaultId(Vault vault);
	
	boolean addPaymentMethod(Vault v, PaymentMethod pm, String tipo);
	
	boolean deletePaymentMethod(Vault v, PaymentMethod pm, String tipo);
}
