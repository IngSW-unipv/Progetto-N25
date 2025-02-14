package it.unipv.ingsw.lasout.facade.vault;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.group.ConcreteGroupFacade;
import it.unipv.ingsw.lasout.facade.virtualVault.ConcreteVirtualVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.IVaultDAO;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.vault.VaultDAO;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.CreditCard;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethodFactory;
import it.unipv.ingsw.lasout.model.virtualVault.IVirtualVaultDAO;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.util.DaoFactory;

public class ConcreteVaultFacade implements VaultFacade {

	private VaultDAO vaultDAO;
	private PaymentMethodFactory paymentMethodFactory;

	public ConcreteVaultFacade() {
		vaultDAO = (VaultDAO) DaoFactory.getVaultDAO();
		paymentMethodFactory = new PaymentMethodFactory();
	}

	private static ConcreteVaultFacade instance;

	public static ConcreteVaultFacade getInstance() {
		if (instance == null) {
			instance = new ConcreteVaultFacade();
		}
		return instance;
	}

	@Override
	public boolean sendMoney(User Sender, User Receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean newVaultinVirtualVault(Vault vault, User user) {

		try {
			if (LaVaultFacade.getInstance().getSessionFacade().isLogged()) {
				vaultDAO.save(vault);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean newVaultinVault(Vault vault) {
		
		try {
			int id = VaultDAO.getInstance().vaultId(vault);
			vault.setVv_id(id);
			vaultDAO.saveInVault(vault);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean addPaymentMethod(Vault v, PaymentMethod pm, String tipo) {
		
		paymentMethodFactory.get(tipo); 
		
		if (pm != null) {
			
            v.getMethods().add(pm);
            
            try {
				pm.save(pm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	
		
		return true;
	}

	private static final Logger LOGGER = Logger.getLogger(ConcreteVaultFacade.class.getName());

	public static void main(String[] args) {

		try {
			DatabaseUtil.getInstance().prepare();
			DatabaseUtil.getInstance().initialize();
		} catch (IOException | SQLException e) {
			LOGGER.severe("Couldn't initialize database: \n" + e);
			System.exit(1);
			return;
		}

		Vault v = new Vault();

		User u = new User("buso", "pluto", "ddd@gmail.com");
		LaVaultFacade.getInstance().getSessionFacade().login(u);
		System.out.println("It's logged in " + LaVaultFacade.getInstance().getSessionFacade().isLogged());

		System.out.println("questo è il mio id " + LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId());

		v.setOwner(new User(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId()));
		
		ConcreteVaultFacade.getInstance().newVaultinVirtualVault(v, u);
		
		ConcreteVaultFacade.getInstance().newVaultinVault(v);
		
		// da recuperare l'id del vault, se no non funziona, RICORDAAAAAAAA
		
		PaymentMethod pm = new CreditCard("3647483", 2, 2021, 345, v.getId());
		
		System.out.println(""+ pm.getMethodName());
		
		ConcreteVaultFacade.getInstance().addPaymentMethod(v, pm, pm.getMethodName());
		
	}

}
