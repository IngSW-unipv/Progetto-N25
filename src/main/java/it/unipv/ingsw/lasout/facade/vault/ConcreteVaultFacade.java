package it.unipv.ingsw.lasout.facade.vault;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.unipv.ingsw.lasout.controller.vault.VaultController;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.group.ConcreteGroupFacade;
import it.unipv.ingsw.lasout.facade.virtualVault.ConcreteVirtualVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.IVaultDAO;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.vault.VaultDAO;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.CreditCard;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.CurrentAccount;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PayPal;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethodDAO;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethodFactory;
import it.unipv.ingsw.lasout.model.virtualVault.IVirtualVaultDAO;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.util.DaoFactory;
import it.unipv.ingsw.lasout.model.transaction.*;


public class ConcreteVaultFacade implements VaultFacade {

	private IVaultDAO vaultDAO;
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
	public Vault getVault(Vault v) {
		try {
            return vaultDAO.get(v);
        } catch (Exception e) {
            return null;
        }
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
			int id = VaultDAO.getInstance().getVaultID(vault);
			vault.setVv_id(id);
			vaultDAO.saveInVault(vault);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println("" + vault.getVv_id());
		return true;
	}

	@Override
	public boolean getVaultId(Vault vault) {

		try {
			int id = VaultDAO.getInstance().vaultIdinVault(vault);
			vault.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println("" + vault.getId());

		return true;
	}

	@Override
	public boolean addPaymentMethod(Vault v, PaymentMethod pm, String tipo) {

		paymentMethodFactory.get(tipo);

		if (pm != null) {

			v.getMethods().add(pm);

			try {
				pm.save(pm);

				pm.getId(pm);

				pm.saveInPaymentMethod(v, pm);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean deletePaymentMethod(Vault v, PaymentMethod pm, String tipo) {

		paymentMethodFactory.get(tipo);

		if (pm != null) {

			try {
				pm.getId(pm);

				pm.delete(pm);

				pm.deleteInPaymentMethod(pm);

				v.getMethods().remove(pm);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean depositMoneyFromPaymentMethod(Vault v, PaymentMethod p, double balance) {
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = today.format(formatter);
		

		if (p.autorizza() != true)
			throw new RuntimeException("Errore: Autorizzazione negata");

		try {
			vaultDAO.updateBalance(v, balance);

			v.setSaldo(v.getSaldo() + balance);
			
			AutomaticTransaction t = new AutomaticTransaction(balance, formattedDate, "Deposit");
			LaVaultFacade.getInstance().getCashbookFacade().addTransaction(v.getOwner(), t);

			System.out.println("Fondi aggiunti con successo! Nuovo saldo: " + v.getSaldo());

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean withdrawMoneyFromPaymentMethod(Vault v, PaymentMethod p, double amount) {

		if (p.autorizza() != true)
			throw new RuntimeException("Errore: Autorizzazione negata");

		if (v.getSaldo() < amount) {
			System.out.println("Fondi insufficienti! Saldo disponibile: " + v.getSaldo());
			return false;
		}

		try {
			
			vaultDAO.withdrawBalance(v, amount);
			v.setSaldo(v.getSaldo() - amount);
			
			System.out.println("Prelievo completato! Nuovo saldo: " + v.getSaldo());
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	

//	@Override
//	public Vault getVaultByUserID(Vault vault) {
//		
//		Vault v = null;
//		
//		try {
//			int id = vaultDAO.getVaultIDbyUser(vault);
//			vaultDAO.getRaw(new Vault(id));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		return v;
//	}
	
	@Override
	public Vault getVaultByUser(User user) {
	    try {
	        return VaultDAO.getInstance().getVaultIDbyUser(user);
	    } catch(Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	@Override
	public double getBalanceByID(Vault v) {
		
		double balance = 0;
		
		try {
			balance = vaultDAO.getBalance(v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return balance;
		
	}
	

	@Override
	public List<PaymentMethod> getAllPaymentMethods(Vault vault) {
		
		List<PaymentMethod> methods = new ArrayList<PaymentMethod>();
		
		try {
			methods = PaymentMethodDAO.getInstance().getAllPaymentMethod(vault);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return methods;
	}
	


	@Override
	public boolean executePayment(Vault v, double amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public boolean paymenExecution(Vault v, double amount, String recipient) {
		// TODO Auto-generated method stub
		return false;
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

		User u = new User("dada", "ciao", "aaa@gmail.com");
		Vault v = new Vault();

		LaVaultFacade.getInstance().getSessionFacade().login(u);
		System.out.println("It's logged in " + LaVaultFacade.getInstance().getSessionFacade().isLogged());

		v.setOwner(new User(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId()));

		ConcreteVaultFacade.getInstance().newVaultinVirtualVault(v, u);

		ConcreteVaultFacade.getInstance().newVaultinVault(v);

		ConcreteVaultFacade.getInstance().getVaultId(v);
		
		double c = ConcreteVaultFacade.getInstance().getBalanceByID(v);
		
		System.out.println("" + c);
		
		
	//	int id = ConcreteVaultFacade.getInstance().getVaultByUserID(v);
		
		//System.out.println("" + id);

		PaymentMethod pm = new CreditCard("3647483", 2, 2021, 345, v.getId());
		PaymentMethod ca = new CurrentAccount("IT38298326238965289", v.getId());
		PaymentMethod pp = new PayPal("369837431", v.getId());

		ConcreteVaultFacade.getInstance().addPaymentMethod(v, pm, pm.getMethodName());
		ConcreteVaultFacade.getInstance().addPaymentMethod(v, ca, ca.getMethodName());
		ConcreteVaultFacade.getInstance().addPaymentMethod(v, pp, pp.getMethodName());
		
//		System.out.println("" + v.getVv_id());
//		System.out.println("" + v.getId());
		
		try {
			VaultDAO.getInstance().getRaw(v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//
//		for (PaymentMethod p : v.getMethods()) {
//			System.out.println("Metodo di pagamento: " + p.getMethodName());
//		}
//
		System.out.println("Saldo iniziale: " + v.getSaldo());
//
		boolean success = ConcreteVaultFacade.getInstance().depositMoneyFromPaymentMethod(v, pm, 100.50);

		if (success) {
			System.out.println("Saldo aggiornato: " + v.getSaldo());
		} else {
			System.out.println("Errore nell'aggiunta dei fondi.");
		}
//		
//		boolean success1 = ConcreteVaultFacade.getInstance().withdrawMoneyFromPaymentMethod(v, pp, 50);
//
//		if (success1) {
//			System.out.println("Saldo aggiornato: " + v.getSaldo());
//		} else {
//			System.out.println("Errore nell'aggiunta dei fondi.");
//		}
		// ConcreteVaultFacade.getInstance().deletePaymentMethod(v, pm,
		// pm.getMethodName());
		// ConcreteVaultFacade.getInstance().deletePaymentMethod(ca,
		// ca.getMethodName());
		// ConcreteVaultFacade.getInstance().deletePaymentMethod(pp,
		// pp.getMethodName());

//		for (PaymentMethod p : v.getMethods()) {
//		    System.out.println("Metodo di pagamento: " + p.getMethodName());
//		}
	}
}
