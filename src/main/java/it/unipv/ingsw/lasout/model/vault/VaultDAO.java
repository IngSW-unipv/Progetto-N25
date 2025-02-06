package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVaultDAO;

import java.sql.ResultSet;
import java.util.List;


public class VaultDAO implements IVaultDAO{

	private static final VaultDAO INSTANCE = new VaultDAO();
    public static VaultDAO getInstance(){
        return INSTANCE;
    }
    
    private static final String Query1 = "SELECT * FROM virtualvault WHERE id = ?;";
    

	@Override
	public Vault getRaw(Vault oggetto) throws Exception {
		
		// mi devo interfacciare verso il DAO del VirtualVault, per capire se l'id inserito appartiene ad un Vault o meno
		VirtualVault vault  =  VirtualVaultDAO.INSTANCE.getRaw(new VirtualVault(oggetto.getID(), new User(oggetto.getOwner().getId())));
		
		DBQuery query = DatabaseUtil.getInstance().createQuery(Query1, oggetto.getID());
		
		DatabaseUtil.getInstance().executeQuery(query);
		
		ResultSet result = query.getResultSet();
		
		// nel database, l'id messo non corrisponde ad un vault
		if(result == null) throw new RuntimeException("This is not a vault");
		
		// se supera l'eccezione, vuol dire che nel result ho un vault, quindi posso restituirlo
		
		return oggetto;
		
	}

	@Override
	public Vault get(Vault vault) throws Exception {
		return null;
		
	}
	
	@Override
	public List<Vault> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void save(Vault t) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Vault t, String[] params) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Vault t) throws Exception {
		// TODO Auto-generated method stub
		
	} 

}
