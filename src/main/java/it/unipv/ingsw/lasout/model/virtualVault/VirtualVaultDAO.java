package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.vault.Vault;

import java.sql.ResultSet;
import java.util.List;

public class VirtualVaultDAO implements IVirtualVaultDao {
    public static final VirtualVaultDAO INSTANCE = new VirtualVaultDAO();
    public static VirtualVaultDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public VirtualVault getRaw(VirtualVault oggetto) throws Exception {
        return null;
    }

    //commento prova
    @Override
    public VirtualVault get(VirtualVault virtualVault) throws Exception {

        DBQuery query =  DatabaseUtil.getInstance().createQuery("SELECT  " +
                "FROM `` " +
                "WHERE id = ;", virtualVault.getId());

        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) throw new RuntimeException("Could not find a vault by  id = '" + virtualVault.getId() + "'");

        int id = resultSet.getInt("id");
        VirtualVault loadedVirtualVault = new VirtualVault(virtualVault.getId());

        query.close();
        return loadedVirtualVault;
    }

    @Override
    public List<VirtualVault> getAll() throws Exception {
        return List.of();
    }

    @Override
    public void save(VirtualVault virtualVault) throws Exception {

    }

    @Override
    public void update(VirtualVault virtualVault, String[] params) throws Exception {

    }

    @Override
    public void delete(VirtualVault virtualVault) throws Exception {

    }


}
