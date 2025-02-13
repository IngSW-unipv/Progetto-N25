package it.unipv.ingsw.lasout.facade.vault;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;

public interface VaultFacade {
	
	boolean sendMoney(User Sender, User Receiver);
	
	boolean newVaultinVirtualVault(Vault vault, User user);

}
