package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;

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
    
    private static final String GET_RAW = "SELECT * FROM £virtualvault£ WHERE id = ?;";
    private static final String GET_ALL_VAULT = "SELECT * FROM £virtualvault£, £vault£ WHERE virtualvault.id = vault.id" ;
    private static final String INSERT_A_NEW_VAULT = "INSERT INTO £virtualvault£ (id) VALUES (?)";
    private static final String DELETE_AN_EXISTING_VAULT = "DELETE FROM £virtualvault£ WHERE id = ?";
    

	@Override
	public Vault getRaw(Vault oggetto) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_RAW, oggetto.getID());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		// nel database, l'id messo non corrisponde ad un vault
		if(result == null) throw new RuntimeException("This is not a vault");
		
		// se supera l'eccezione, vuol dire che nel result ho un vault, quindi posso restituirlo
		
		Vault vault = new Vault();
		
		vault.setID(result.getInt("id"));
		
		return vault;
		
	}

	@Override
	public Vault get(Vault vault) throws Exception {
		return null;
		
	}
	
	@Override
	public List<Vault> getAll() throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_VAULT);
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		List<Vault> vaults = new ArrayList<Vault>();
		
		while (result.next()) {
			Vault vault = new Vault();
			vault.setID(result.getInt("id"));
		}
		
		query.close();
	
		return vaults;
	}
	
	@Override
	public void save(Vault oggetto) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(INSERT_A_NEW_VAULT, oggetto.getID());
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		if(result != null) throw new Exception();
				
		query.close();
		
	}
	
	@Override
	public void update(Vault t, String[] params) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Vault t) throws Exception {
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(DELETE_AN_EXISTING_VAULT);
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		query.close();
		
	} 
	private static final Logger LOGGER = Logger.getLogger(VaultDAO.class.getName());
	public static void main(String []args) throws Exception {

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
