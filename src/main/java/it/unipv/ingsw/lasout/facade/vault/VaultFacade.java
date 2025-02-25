package it.unipv.ingsw.lasout.facade.vault;

import java.util.List;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;

public interface VaultFacade {
	
	Vault getVault(Vault v);
	
	boolean sendMoney(User Sender, User Receiver);
	
	boolean newVaultinVirtualVault(Vault vault, User user);
	
	boolean newVaultinVault(Vault vault);
	
	boolean getVaultId(Vault vault);
	
	boolean addPaymentMethod(Vault v, PaymentMethod pm, String tipo);
	
	boolean deletePaymentMethod(Vault v, PaymentMethod pm, String tipo);
	
	boolean depositMoneyFromPaymentMethod(Vault v, PaymentMethod p, double amount);
	
	boolean withdrawMoneyFromPaymentMethod(Vault v, PaymentMethod p, double amount);
	
	boolean paymenExecution(Vault v, double amount, String recipient); 

	double getBalanceByID(Vault v);

	Vault getVaultByUser(User user);
	
	List<PaymentMethod> getAllPaymentMethods (Vault vault);
	
	boolean executePayment(Vault v, double amount);
}
