package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethodDAO;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VaultDAO implements IVaultDAO{

	private static final VaultDAO INSTANCE = new VaultDAO();
    public static VaultDAO getInstance(){
        return INSTANCE;
    }
    
    private static final String GET_RAW = "SELECT * FROM \\'virtualvault\\' WHERE id = ?;";
    private static final String GET_VAULTID_BY_USER_ID = "SELECT * FROM \\'virtualvault\\' WHERE user_id = ? AND nome = 'Vault'";
    private static final String GET_VAULT_BY_VVID = "SELECT id FROM \\'vault\\' WHERE virtualvault_id = ?";
    private static final String GET_ALL_VAULT_BY_ID = "SELECT vault.id as vaultid FROM \\'virtualvault\\', \\'vault\\' WHERE virtualvault.id = vault.virtualvault_id" ;
    private static final String GET_ALL_VAULT_BY_NAME = "SELECT DISTINCT vault.id FROM \\'vault\\', \\'virtualvault\\' WHERE virtualvault.nome = 'Vault'";
    private static final String INSERT_A_NEW_VAULT_IN_VIRTUALVAULT = "INSERT INTO \\'virtualvault\\' (nome, user_id, balance) VALUES ('Vault', ?, 0)";
    private static final String INSERT_A_NEW_VAULT = "INSERT INTO \\'vault\\' (virtualvault_id) VALUES (?)";
    private static final String DELETE_AN_EXISTING_VAULT = "DELETE FROM £virtualvault£ WHERE id = ?";
    private static final String UPDATE_BALANCE = "UPDATE virtualvault SET balance = balance + ? WHERE id = ?";
    private static final String UPDATE_BALANCE_USERID = "UPDATE virtualvault SET balance = balance + ? WHERE user_id = ? AND nome = 'Vault'";
    private static final String WITHDRAW_BALANCE = "UPDATE virtualvault SET balance = balance - ? WHERE id = ? AND balance >= ?";
    private static final String WITHDRAW_BALANCE_USERID = "UPDATE virtualvault SET balance = ? WHERE user_id = ? AND nome = 'Vault'";
    private static final String GET_BALANCE = "SELECT balance FROM \\'virtualvault\\' WHERE id = ?";
    private static final String GET_BALANCE_BY_USER_ID = "SELECT balance FROM \\'virtualvault\\' WHERE user_id = ? AND nome = 'Vault'";
    
    
	public List<Vault> getAllbyName() throws Exception {
			
			DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_VAULT_BY_NAME);
			DatabaseUtil.getInstance().executeQuery(query);
			
			ResultSet result = query.getResultSet();
		
			List<Vault> vaults = new ArrayList<Vault>();
			
			if (result == null) {
		        System.out.println("Errore: ResultSet è null!");
		        return vaults; // Restituisco lista vuota
		    }
			
			
			while (result.next()) {
		        Vault vault = new Vault();
	
		        
		        int id = result.getInt("id");
		        
		        vault.setID(id);
		        vaults.add(vault);
		    }
			
			query.close();
		
			return vaults;
		}
	
	@Override
	public double balanceVault(User user) throws Exception{
		 DBQuery query = DatabaseUtil.getInstance().createQuery(GET_BALANCE_BY_USER_ID, user.getId());
		 DatabaseUtil.getInstance().executeQuery(query);
		 
		 ResultSet result = query.getResultSet();
		 
		 if(result == null  || !result.next()) throw new RuntimeException("Error");
		 
		 double balance = result.getDouble("balance");
		 
		 return balance;
		
	}
	
	@Override
	public void updateBalance(Vault vault, double amount) throws Exception {
	    DBQuery query = DatabaseUtil.getInstance().createQuery(UPDATE_BALANCE, amount, vault.getVv_id());
	    DatabaseUtil.getInstance().executeQuery(query);

	    if (query.getResultSet() != null) throw new Exception("Errore nell'aggiornamento del balance.");

	    query.close();
	}
	
	@Override
	public void depositBalanceWithUser(User user, double amount) throws Exception{
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(UPDATE_BALANCE_USERID, amount, user.getId());
	    DatabaseUtil.getInstance().executeQuery(query);

	    if (query.getResultSet() != null) throw new Exception("Errore nell'aggiornamento del balance.");

	    query.close();
	}
	
	@Override
	public void withdrawBalanceWithUser(User user, double amount) throws Exception {
		System.out.println(amount);
		DBQuery query = DatabaseUtil.getInstance().createQuery(WITHDRAW_BALANCE_USERID, amount, user.getId());
		DatabaseUtil.getInstance().executeQuery(query);
		
		if(query.getResultSet() != null) throw new Exception("Errore nell'aggiornamento del balance.");
		
		query.close();
		
	}
	
	@Override
	public void withdrawBalance(Vault vault, double amount) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(WITHDRAW_BALANCE, amount, vault.getVv_id(), amount);
		DatabaseUtil.getInstance().executeQuery(query);
		
		if(query.getResultSet() != null) throw new Exception("Errore nell'aggiornamento del balance.");
		
		query.close();
		
	}
	
	

	@Override
	public Vault getRaw(Vault oggetto) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_RAW, oggetto.getVv_id());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		// nel database, l'id messo non corrisponde ad un vault
		if(result == null  || !result.next()) throw new RuntimeException("This is not a vault");
		
		// se supera l'eccezione, vuol dire che nel result ho un vault, quindi posso restituirlo
		Vault vault = new Vault();
		
		vault.setSaldo(result.getDouble("balance"));
		vault.setOwner(new User(result.getInt("user_id")));
		vault.setMethods(PaymentMethodDAO.getInstance().getAllPaymentMethod(vault));
		
		query.close();
		
		System.out.println("" + vault.getSaldo() + " " + vault.getOwner() + " " + vault.getMethods());
		
		return vault;
		
	}

	@Override
	public Vault get(Vault vault) throws Exception {
		
		return null;
		
	}
	
	@Override
	public List<Vault> getAll() throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_VAULT_BY_ID);
		DatabaseUtil.getInstance().executeQuery(query);
		
		if (query == null) {
	        throw new RuntimeException("Errore: la query è NULL!");
	    }
		
		ResultSet result = query.getResultSet();
	
		List<Vault> vaults = new ArrayList<Vault>();
		
		if (result == null) {
	        System.out.println("Errore: ResultSet è null!");
	        return vaults; // Restituisco lista vuota
	    }
		
		while (result.next()) {
	        Vault vault = new Vault();

	        // DEBUG: Stampo per vedere il valore letto dal DB
	        int id = result.getInt("vaultid");
	        
	        vault.setID(id);
	        vaults.add(vault);
	    }
		
		query.close();
	
		return vaults;
	}
	
	@Override
	public void save(User user) throws Exception {
		
		System.out.println(user.getId());
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(INSERT_A_NEW_VAULT_IN_VIRTUALVAULT, user.getId());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if(result != null) throw new Exception();
				
		query.close();
		
	}
	
	@Override
	public void saveInVault(Vault oggetto) throws Exception {
		DBQuery query = DatabaseUtil.getInstance().createQuery(INSERT_A_NEW_VAULT, oggetto.getVv_id());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if(result != null) throw new Exception();
				
		query.close();
	}
	
	@Override
	public int getVaultID(Vault oggetto) throws Exception{
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_VAULTID_BY_USER_ID, oggetto.getOwner().getId());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if (result == null) {
	        System.out.println("Errore: ResultSet è null!");
	    }
		
		if (!result.next()) {
		    System.out.println("Nessun record trovato per l'utente specificato.");
		    throw new SQLException("Nessun record trovato per l'utente specificato.");
		}
		
		int idVault = result.getInt("id");
		
		return idVault;		
	}
	
	@Override
	public Vault getVaultIDbyUser(User user) throws Exception{

		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_VAULTID_BY_USER_ID, user.getId());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if (result == null) {
	        System.out.println("Errore: ResultSet è null!");
	    }
		
		if (!result.next()) {
		    System.out.println("Nessun record trovato per l'utente specificato.");
		    throw new SQLException("Nessun record trovato per l'utente specificato.");
		}
		
		 Vault v = new Vault();
		 v.setSaldo(result.getDouble("balance"));         // Imposta il saldo
		 v.setVv_id(result.getInt("id"));                // O usa il nome della colonna corretta per vv_id
		 v.setOwner(new User(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId()));   
		 
		 query.close();
		 
		 System.out.println(""+ v.getSaldo() + v.getVv_id() + v.getOwner());

		return v;		
	}
	

	
	public int vaultIdinVault(Vault oggetto) throws Exception{
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_VAULT_BY_VVID, oggetto.getVv_id());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if (result == null) {
	        System.out.println("Errore: ResultSet è null!");
	    }
		
		if (!result.next()) {
		    System.out.println("Nessun record trovato per il virtual vault specificato.");
		    throw new SQLException("Nessun record trovato per il virtualvault specificato.");
		}
		
		int idVVault = result.getInt("id");
		
		query.close();
		
		return idVVault;	
	}

	@Override
	public void update(Vault vault) throws Exception {

	}

	@Override
	public void delete(Vault t) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(DELETE_AN_EXISTING_VAULT,  t.getID());
		
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if(result != null) throw new Exception();
		
		query.close();
		
	} 
	
	@Override
	public double getBalance(Vault v) throws Exception{
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_BALANCE, v.getVv_id());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if (result == null) {
	        System.out.println("Errore: ResultSet è null!");
	    }
		
		if (!result.next()) {
		    System.out.println("Nessun record trovato per il virtual vault specificato.");
		    throw new SQLException("Nessun record trovato per il virtualvault specificato.");
		}
		
		double balance = result.getDouble("balance");
		
		query.close();
		
		return balance;	
		
	}

	@Override
	public void save(Vault t) throws Exception {
		// TODO Auto-generated method stub
		
	}

}