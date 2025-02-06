package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.model.vault.Vault;

import java.sql.ResultSet;
import java.util.List;

public class VirtualVaultDAO implements IDao<VirtualVault> {
    public static final VirtualVaultDAO INSTANCE = new VirtualVaultDAO();
    public static VirtualVaultDAO getInstance() {
        return INSTANCE;
    }


    //Query per prendere il virtualVault corretto e l'utente
    private static final String QUERY_RAW_1 =
            "SELECT * FROM virtual_vault WHERE id = ? AND user_id = ?";

    @Override
    public VirtualVault getRaw(VirtualVault oggetto) throws Exception {
        //Creo la query raw per prendere i dati e gli passo oggetto che prende ID, owner e l'accoppiata dei due
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_RAW_1, oggetto.getID(), oggetto.getOwner().getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();

        int id = rs.getInt("ID");
        User user = UserDAO.getInstance().get(new User(rs.getInt("user_id")));
        double balance = rs.getDouble("balance");

        VirtualVault vVault = new VirtualVault(id, user);
        vVault.setBalance(balance);

        return vVault;
    }



    @Override
    public VirtualVault get(VirtualVault virtualVault) throws Exception {
        return getRaw(virtualVault);
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
