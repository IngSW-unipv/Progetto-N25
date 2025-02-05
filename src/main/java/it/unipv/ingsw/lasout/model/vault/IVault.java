package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.model.user.User;

public interface IVault {

	// metodo per prendere l'id del Vault
    int getID();
    
    // metodo per settare l'id del Vault
    void setID(int id);
    
    // metodo per prendere il saldo del Vault
    double getBalance();
    
    // metodo per settare il saldo del Vault
    void setBalance(double balance);

    // metodo per avere il proprietario del Vault
    User getOwner();
    
    // metodo per settare il proprietario del Vault
    public void setOwner(User owner);

    IVaultData getIVaultData();
    void setIVaultData(IVaultData ivaultData);
    
}
