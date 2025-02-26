package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.user.User;

public interface IVaultDAO extends IDao<Vault>{

	int getVaultID(Vault oggetto) throws Exception;

	void updateBalance(Vault vault, double amount) throws Exception;

	void withdrawBalance(Vault vault, double amount) throws Exception;

	void saveInVault(Vault oggetto) throws Exception;

	double getBalance(Vault v) throws Exception;

	Vault getVaultIDbyUser(User user) throws Exception;

	double balanceVault(User user) throws Exception;

	void withdrawBalanceWithUser(User user, double amount) throws Exception;

	void depositBalanceWithUser(User user, double amount) throws Exception;

}
