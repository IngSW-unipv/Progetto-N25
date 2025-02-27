package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.user.User;
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
    private static final String QUERY_RAW_1 = "SELECT * FROM $virtualvault$ WHERE id = ? && user_id = ? ";
    private static final String QUERY_INSERT_NEW_VIRTUALVAULT_NOID= "INSERT INTO $virtualvault$ (nome, user_id, balance) VALUES (?, ?, ?)";
    private static final String QUERY_INSERT_NEW_VIRTUALVAULT_ID= "INSERT INTO $virtualvault$ (id, nome, user_id, balance) VALUES (?, ?, ?, ?)";
    private static final String QUERY_DELETE_AN_EXISTING_VIRTUALVAULT = "DELETE FROM $virtualvault$ WHERE id = ?";
    private static final String QUERY_DELETE_AN_EXISTING_VIRTUALVAULT_BALANCE = "DELETE FROM $virtualvault$ WHERE id = ? AND nome = 'Vault'";
    private static final String GET_ALL_VIRTUALVAULT =  "SELECT * FROM £virtualvault£ WHERE user_id = ? && nome != 'Vault'" ;
    private static final String GET_BALANCE_FROM_VAULT =  "SELECT balance FROM £virtualvault£ WHERE  user_id = ? AND nome = ?" ;
    private static final String UPDATE_BALANCE = "UPDATE $virtualvault$ SET balance = ? WHERE id = ?";
    private static final String GET_ID_USERID_FROM_VVP =  "SELECT id, user_id, nome FROM £virtualvault£  WHERE user_id = ? AND nome = 'Vault'";

    @Override
    public VirtualVault getRaw(VirtualVault virtualVault) throws Exception {
        //Creo la query raw per prendere i dati e gli passo oggetto che prende ID, owner e l'accoppiata dei due
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_RAW_1, virtualVault.getID(), virtualVault.getOwner().getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();

        /*int id = rs.getInt("ID");
        User user = userDAO.get(new User(rs.getInt("user_id")));

        double balance = rs.getDouble("balance");

        VirtualVault vVault = new VirtualVault(id, user);
        vVault.setBalance(balance);
        return vVault;*/
        if (rs.next()) { // Assicurati che ci sia almeno una riga
            int id = rs.getInt("ID");
            String nome = rs.getString("NOME");
            User user = userDAO.get(new User(rs.getInt("user_id")));
            double balance = rs.getDouble("balance");
            VirtualVault vVault = new VirtualVault(id, nome, user);
            vVault.setName(nome);
            vVault.setID(id);
            vVault.setBalance(balance);
            return vVault;
        } else {
            return null;
        }

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
    public List<VirtualVault> getAll(User user) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_VIRTUALVAULT, user.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();

        if(rs == null) throw new SQLException("querySelect error");

        List<VirtualVault> virtualVaults = new ArrayList<VirtualVault>();

        while (rs.next()) {

            VirtualVault vVault = new VirtualVault();
            vVault.setID(rs.getInt("id"));
            vVault.setBalance(rs.getDouble("balance"));
            vVault.setName(rs.getString("nome"));
            vVault.setOwner(userDAO.getRaw(new User(rs.getInt("user_id"))));

            virtualVaults.add(vVault);
        }

        query.close();
        return virtualVaults;
    }


    /*
    * Metodo per ottenere il balance originario dal vault
    */
    @Override
    public double getBalanceFromVault(VirtualVault virtualVault) throws Exception{
        double b = 0.0;
        DBQuery queryGetBalanceFromVault;
        queryGetBalanceFromVault = DatabaseUtil.getInstance().createQuery(GET_BALANCE_FROM_VAULT,   virtualVault.getOwner().getId(), virtualVault.getName());
        DatabaseUtil.getInstance().executeQuery(queryGetBalanceFromVault);
        if(queryGetBalanceFromVault.getResultSet().next()){
            b = queryGetBalanceFromVault.getResultSet().getDouble("balance");
        }
        queryGetBalanceFromVault.close();
        return b;
    }

    /*
    * Metodo per impostare il nuovo balance nell vault originario
    */
    @Override
    public void setNewBalanceVault(VirtualVault virtualVault) throws Exception {
        DBQuery queryUp;
        queryUp = DatabaseUtil.getInstance().createQuery(UPDATE_BALANCE,  virtualVault.getBalance(), virtualVault.getID(), virtualVault.getOwner().getId());
        DatabaseUtil.getInstance().executeQuery(queryUp);
        ResultSet rs = queryUp.getResultSet();
        queryUp.close();
    }

    /*
     * Metodo che serve per la generazione di un nuovo virtualVault con i dati aggiornati con la sottrazione
     */
    @Override
    public void getIdFromVvP(VirtualVault virtualVault, double b, DBQuery queryGet) throws Exception {
        ResultSet rsGet = queryGet.getResultSet();
        while(rsGet.next()){
            VirtualVault vVaultOrigin = new VirtualVault();
            vVaultOrigin.setID(rsGet.getInt("id"));
            vVaultOrigin.setOwner(userDAO.get(new User(rsGet.getInt("user_id"))));
            vVaultOrigin.setName(rsGet.getString("nome"));
            vVaultOrigin.setBalance(b - virtualVault.getBalance() );
            setNewBalanceVault(vVaultOrigin);
        }
    }

    /*
     * Metodo che serve per la generazione di un nuovo virtualVault con i dati aggiornati con la somma
     */
    @Override
    public void getIdFromVvPPLUS(VirtualVault virtualVault, double b, DBQuery queryGet) throws Exception {
        ResultSet rsGet = queryGet.getResultSet();
        while(rsGet.next()){
            VirtualVault vVaultOrigin = new VirtualVault();
            vVaultOrigin.setID(rsGet.getInt("id"));
            vVaultOrigin.setOwner(userDAO.get(new User(rsGet.getInt("user_id"))));
            vVaultOrigin.setName(rsGet.getString("nome"));
            vVaultOrigin.setBalance(b + virtualVault.getBalance() );
            setNewBalanceVault(vVaultOrigin);
        }
    }

    /*
    * Metodo per salvare un nuovo virtualVault, che sfrutta altri metodi all'interno del programma
    */
    @Override
    public void save(VirtualVault virtualVault) throws Exception {
        //Query per l'aggiunta di un virtualvault
        DBQuery queryInsert;
        DBQuery queryGet;

        if (virtualVault.getID() != 0) {
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_VIRTUALVAULT_ID, virtualVault.getID(), virtualVault.getName(), virtualVault.getOwner().getId(), virtualVault.getBalance());
            DatabaseUtil.getInstance().executeQuery(queryInsert);
            ResultSet rs = queryInsert.getResultSet();

            queryGet = DatabaseUtil.getInstance().createQuery(GET_ID_USERID_FROM_VVP, virtualVault.getOwner().getId());
            DatabaseUtil.getInstance().executeQuery(queryGet);



            if(rs!=null)throw new Exception();
            if(virtualVault.getID()==0) virtualVault.setID((int)queryInsert.getKey());


            queryInsert.close();

        } else {
            queryInsert = DatabaseUtil.getInstance().createGeneratedKeyQuery(QUERY_INSERT_NEW_VIRTUALVAULT_NOID, virtualVault.getName(), virtualVault.getOwner().getId(), virtualVault.getBalance());
            DatabaseUtil.getInstance().executeQuery(queryInsert);
            ResultSet rs = queryInsert.getResultSet();
            //virtualVault.setBalance(b - virtualVault.getBalance() );
            //System.out.println("update = " + virtualVault.getBalance());

            queryGet = DatabaseUtil.getInstance().createQuery(GET_ID_USERID_FROM_VVP, virtualVault.getOwner().getId());
            DatabaseUtil.getInstance().executeQuery(queryGet);


            if(rs!=null)throw new Exception();
            if(virtualVault.getID()==0) virtualVault.setID((int)queryInsert.getKey());
            queryInsert.close();
        }
    }
    /*
    * Metodo di update
    */
    @Override
    public void update(VirtualVault virtualVault) throws Exception {

        DBQuery query = DatabaseUtil.getInstance().createQuery(UPDATE_BALANCE, virtualVault.getBalance(), virtualVault.getID());
        DatabaseUtil.getInstance().executeQuery(query);

        if (query.getResultSet() != null) throw new Exception("Errore nell'aggiornamento del balance.");

        query.close();
    }


    /**
     * Metodo per eliminare il virtualVault selezionato ma prima viene riaggiunto il balance al Vault principale
     * @param virtualVault
     * @throws Exception
     */
    @Override
    public void delete(VirtualVault virtualVault) throws Exception {
        //Parte di metodo per riaggiungere il balance al vault originario


            DBQuery queryDelete = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_AN_EXISTING_VIRTUALVAULT, virtualVault.getID());
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
        VirtualVault v2 = new VirtualVault();;

        v.setName("IL PRIMO");
        v.setOwner(new User(1));
        ;
        v.setBalance(50);
        v2.setName("IL SECONDO");
        v2.setOwner(new User(2));
        v2.setBalance(50);

        VirtualVaultDAO.getInstance().save(v);


        VirtualVaultDAO.getInstance().save(v2);
        //VirtualVaultDAO.getInstance().delete(v2);
        VirtualVaultDAO.getInstance().getAll();



    }


}
