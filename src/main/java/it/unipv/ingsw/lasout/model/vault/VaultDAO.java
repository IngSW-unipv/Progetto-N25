package it.unipv.ingsw.lasout.model.vault;

import it.unipv.ingsw.lasout.dao.DBQuery;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class VaultDAO implements IVaultDAO{

	private static final VaultDAO INSTANCE = new VaultDAO();
    public static VaultDAO getInstance(){
        return INSTANCE;
    }
    
	@Override
	public Vault get(Vault vault) throws Exception {
		
		DBQuery query =  DatabaseUtil.getInstance().createQuery("SELECT * " +
                "FROM `vault` " +
                "WHERE id = ?;", vault.getId());
		
        DatabaseUtil.getInstance().executeQuery(query);
        
        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) throw new RuntimeException("Could not find a vault by  id = '" + vault.getId() + "'");

        int id = resultSet.getInt("id");
        Vault loadedVault = new Vault(vault.getId());

        query.close();
        return loadedVault;
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
