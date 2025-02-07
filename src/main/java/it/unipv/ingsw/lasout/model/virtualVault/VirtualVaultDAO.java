package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.model.vault.Vault;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class VirtualVaultDAO implements IDao<VirtualVault> {
    public static final VirtualVaultDAO INSTANCE = new VirtualVaultDAO();
    public static VirtualVaultDAO getInstance() {
        return INSTANCE;
    }


    //Varie query
    private static final String QUERY_RAW_1 = "SELECT * FROM $virtualvault$ WHERE id = ? AND user_id = ?";
    private static final String QUERY_INSERT_NEW_VIRTUALVAULT_NOID= "INSERT INTO $virtualvault$ (user_id, balance) VALUES (?, ?)";
    private static final String QUERY_INSERT_NEW_VIRTUALVAULT_ID= "INSERT INTO $virtualvault$ (id, user_id, balance) VALUES (?, ?, ?)";
    private static final String QUERY_DELETE_AN_EXISTING_VIRTUALVAULT =      "DELETE FROM $virtualvault$ WHERE id = ?";

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
        //Query per l'aggiunta di un virtualvault


        DBQuery queryInsert;

        if(virtualVault.getID()!=0){
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_VIRTUALVAULT_ID, virtualVault.getID(), virtualVault.getOwner().getId(), virtualVault.getBalance());
        }else{
            queryInsert = DatabaseUtil.getInstance().createGeneratedKeyQuery(QUERY_INSERT_NEW_VIRTUALVAULT_NOID, virtualVault.getOwner().getId(), virtualVault.getBalance());
        }

        DatabaseUtil.getInstance().executeQuery(queryInsert);

        ResultSet rs = queryInsert.getResultSet();

        if(rs!=null)throw new Exception();
        if(virtualVault.getID()==0) virtualVault.setID((int)queryInsert.getKey());
        //Chiusura query
        queryInsert.close();
    }

    @Override
    public void update(VirtualVault virtualVault) throws Exception {

    }


    @Override
    public void delete(VirtualVault virtualVault) throws Exception {
        //Query per l'aggiunta di un virtualvault
        DBQuery queryDelete = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_AN_EXISTING_VIRTUALVAULT, virtualVault.getID(), virtualVault.getOwner());
        DatabaseUtil.getInstance().executeQuery(queryDelete);

        //Chiusura query
        queryDelete.close();
    }


    //Parte per testare progetto
    private static final Logger LOGGER = Logger.getLogger(VirtualVaultDAO.class.getName());
    //main di test
    public static void main(String[] args) throws Exception {
        //controllo iniziale per il DB
        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Can't initialize the DB: \n" + e);
            System.exit(1);
            return;
        }

        VirtualVault v = new VirtualVault();;

        v.setBalance(200);
        v.setOwner(new User(1));
        v.setID(1);

        VirtualVaultDAO.getInstance().save(v);



    }


}
