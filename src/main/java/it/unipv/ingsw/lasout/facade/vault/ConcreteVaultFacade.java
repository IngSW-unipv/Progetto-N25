package it.unipv.ingsw.lasout.facade.vault;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.group.ConcreteGroupFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;

public class ConcreteVaultFacade implements VaultFacade{

	@Override
	public boolean sendMoney(User Sender, User Receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean newVaultinVirtualVault(Vault vault, User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private static final Logger LOGGER = Logger.getLogger(ConcreteGroupFacade.class.getName());
	public static void main(String[] args) {
		
		try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Couldn't initialize database: \n" + e);
            System.exit(1);
            return;
        }

	}

}
