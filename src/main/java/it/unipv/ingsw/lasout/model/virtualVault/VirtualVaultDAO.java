package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VirtualVaultDAO implements IVirtualVaultDAO {
    //
    private IUserDAO userDAO;
    //
    public VirtualVaultDAO() {
        userDAO = DaoFactory.getUserDAO();

    }
    public static final VirtualVaultDAO INSTANCE = new VirtualVaultDAO();
    public static VirtualVaultDAO getInstance() {
        return INSTANCE;
    }


    //Varie query
    private static final String QUERY_RAW_1 = "SELECT * FROM $virtualvault$ WHERE id = ? AND user_id = ?";
    private static final String QUERY_INSERT_NEW_VIRTUALVAULT_NOID= "INSERT INTO $virtualvault$ (name, user_id, balance) VALUES (?, ?, ?)";
    private static final String QUERY_INSERT_NEW_VIRTUALVAULT_ID= "INSERT INTO $virtualvault$ (id, name, user_id, balance) VALUES (?, ?, ?, ?)";
    private static final String QUERY_DELETE_AN_EXISTING_VIRTUALVAULT = "DELETE FROM $virtualvault$ WHERE id = ?";
    private static final String GET_ALL_VIRTUALVAULT =  "SELECT * FROM £virtualvault£ WHERE virtualvault.id = ?" ;
    private static final String GET_BALANCE_FROM_VAULT =  "SELECT balance FROM £virtualvault£ WHERE  user_id = ? AND name = 'Vault'" ;

    @Override
    public VirtualVault getRaw(VirtualVault virtualVault) throws Exception {
        //Creo la query raw per prendere i dati e gli passo oggetto che prende ID, owner e l'accoppiata dei due
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_RAW_1, virtualVault.getID(), virtualVault.getOwner().getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();

        int id = rs.getInt("ID");
        User user = userDAO.get(new User(rs.getInt("user_id")));

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
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_VIRTUALVAULT, VirtualVault.class);
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();

        if(rs == null) throw new SQLException("querySelect error");

        List<VirtualVault> virtualVaults = new ArrayList<VirtualVault>();

        while (rs.next()) {

            VirtualVault vVault = new VirtualVault();
            vVault.setID(rs.getInt("id"));
            vVault.setBalance(rs.getDouble("balance"));
            vVault.setOwner(UserDAO.getInstance().get(new User(rs.getInt("user_id"))));

            virtualVaults.add(vVault);
        }

        query.close();
        return virtualVaults;
    }

    @Override
    public double getBalanceFromVault(VirtualVault virtualVault) throws Exception{
        double b = 0.0;
        DBQuery queryGetBalanceFromVault;
        queryGetBalanceFromVault = DatabaseUtil.getInstance().createQuery(GET_BALANCE_FROM_VAULT,   virtualVault.getOwner().getId());
        DatabaseUtil.getInstance().executeQuery(queryGetBalanceFromVault);
        if(queryGetBalanceFromVault.getResultSet().next()){
            b = queryGetBalanceFromVault.getResultSet().getDouble("balance");
        }
        //System.out.println("getBalanceFromVault = " + b);
        queryGetBalanceFromVault.close();
        return b;
    }


    @Override
    public void save(VirtualVault virtualVault) throws Exception {
        //Query per l'aggiunta di un virtualvault
        DBQuery queryInsert;


        double b =  VirtualVaultDAO.getInstance().getBalanceFromVault(virtualVault);
        if(virtualVault.getBalance() <  b) {
            if (virtualVault.getID() != 0) {
                queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_VIRTUALVAULT_ID, virtualVault.getID(), virtualVault.getName(), virtualVault.getOwner().getId(), virtualVault.getBalance());
                DatabaseUtil.getInstance().executeQuery(queryInsert);
                ResultSet rs = queryInsert.getResultSet();
                if(rs!=null)throw new Exception();
                if(virtualVault.getID()==0) virtualVault.setID((int)queryInsert.getKey());
                queryInsert.close();
            } else {
                queryInsert = DatabaseUtil.getInstance().createGeneratedKeyQuery(QUERY_INSERT_NEW_VIRTUALVAULT_NOID, virtualVault.getName(), virtualVault.getOwner().getId(), virtualVault.getBalance());
                DatabaseUtil.getInstance().executeQuery(queryInsert);
                ResultSet rs = queryInsert.getResultSet();
                if(rs!=null)throw new Exception();
                if(virtualVault.getID()==0) virtualVault.setID((int)queryInsert.getKey());
                queryInsert.close();
            }

        }else {
            System.out.println("Importo che vuoi inserire troppo alto  "+virtualVault.getBalance()+"\nNel vault hai a disposizione: "+ VirtualVaultDAO.getInstance().getBalanceFromVault(virtualVault));
        }




        
    }

    @Override
    public void update(VirtualVault virtualVault) throws Exception {

    }


    @Override
    public void delete(VirtualVault virtualVault) throws Exception {
        //Query per l'aggiunta di un virtualvault
        DBQuery queryDelete = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_AN_EXISTING_VIRTUALVAULT, virtualVault.getID());
        DatabaseUtil.getInstance().executeQuery(queryDelete);

        //Chiusura query
        queryDelete.close();
    }
    /*
    public void getBalanceFromVault(Vault vault) throws Exception{
        //Query per l'aggiunta di un virtualvault
        DBQuery querygetBalance = DatabaseUtil.getInstance().createQuery(GET_BALANCE_FROM_VAULT, vault.getID(), vault.getOwner().getId());
        DatabaseUtil.getInstance().executeQuery(querygetBalance);

        //Chiusura query
        querygetBalance.close();
    }*/


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
        VirtualVault v2 = new VirtualVault();;

        v.setName("IL PRIMO");
        v.setOwner(new User(1));
        //v.setID(1);
        v.setBalance(300);
//        v2.setOwner(new User(2));
//        v2.setID(2);
//        v2.setBalance(2000);

        VirtualVaultDAO.getInstance().save(v);
        //VirtualVaultDAO.getInstance().getBalanceFromVault(v);

        //VirtualVaultDAO.getInstance().save(v2);
        //VirtualVaultDAO.getInstance().delete(v2);
        //VirtualVaultDAO.getInstance().getAll();


    }


}
