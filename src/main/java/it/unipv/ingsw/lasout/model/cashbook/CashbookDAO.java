package it.unipv.ingsw.lasout.model.cashbook;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Transaction;

public class CashbookDAO implements ICashbookDAO {
    /**
     * Istanza singola del GroupDao (implementazione singleton)
     */
    private static CashbookDAO instance = null;

    /**
     *
     * @return l'istanza singleton del GroupDao
     */
    public static CashbookDAO getInstance(){
        if (instance == null){
            instance= new CashbookDAO();
        }
        return instance;
    }

    /**
     * Rendo il costruttore privato
     */
    private CashbookDAO(){
        super();
    }


    private static final String GET_ALL_CASHBOOKS = "SELECT * FROM £cashbook£";
    private static final String GET_CASHBOOK_FROM_ID = "SELECT * FROM £cashbook£ WHERE id=?;";
    private static final String GET_TRANSACTIONS_FROM_CASHBOOKTRANSACTIONS = "SELECT * FROM £cashbooktransactions£ WHERE cashbook_id = ?;";

    /**
     * Voglio ottenere un CashBook dal DB solo tramite il suo ID
     * @param fictitiousCashbook ogetto contenente il solo identificatore dell'entità
     * @return un Cashbook contenente solo le informazioni raw, ovvero privo delle sue transazioni
     * @throws Exception Errore nell'esecuzione della query o mancata connessione al db
     */
    @Override
    public Cashbook getRaw(Cashbook fictitiousCashbook) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_CASHBOOK_FROM_ID, fictitiousCashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) {
            throw new RuntimeException("Cashbook not found");
        }

        Cashbook savedCashbook = new Cashbook();
        savedCashbook.setId(resultSet.getInt("id"));
        savedCashbook.setName(resultSet.getString("name"));

        query.close();
        return savedCashbook;
    }

    /**
     * Ricerca di un cashbook dato solamente il suo ID, restituisce il cashbook completo
     * @param carrierCashbook ovvero un cashbook con solo l'ID
     * @return Cashbook cercato
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    @Override
    public Cashbook get(Cashbook carrierCashbook) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_CASHBOOK_FROM_ID, carrierCashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) {
            throw new RuntimeException("Cashbook not found");
        }

        Cashbook savedCashbook = new Cashbook();
        savedCashbook.setId(resultSet.getInt("id"));
        savedCashbook.setName(resultSet.getString("name"));
        savedCashbook.setTransactionList(getTransactionsFromDB(new Cashbook(resultSet.getInt("id"))));

        query.close();
        return savedCashbook;
    }

    /**
     * Voglio una funzione che restituisca tutti i CashBooks presenti nel database
     * @return Lista di tutti i CashBooks presenti nel database
     * @throws Exception Errore nell'esecuzione della query o mancata connessione al db
     */
    @Override
    public List<Cashbook> getAll() throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_CASHBOOKS);
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();
        List<Cashbook> cashbooksList = new ArrayList<Cashbook>();
        while(rs.next()){
            Cashbook cashbook = new Cashbook();
            cashbook.setId(rs.getInt("id"));
            cashbook.setName(rs.getString("name"));
            cashbook.setTransactionList(getTransactionsFromDB(new Cashbook(rs.getInt("id"))));
            cashbooksList.add(cashbook);
        }

        query.close();
        return cashbooksList;
    }

    /**
     * UserGroupDAO: retituisce la lista di transazioni per uno specifico cashbook partendo
     * dalla relazione cashbooktransactions (many to many)
     * @param carrierCashbook carrier contenente solo l'id del Cashbook di cui si vogliono le transazioni
     * @return Lista di transazioni con SOLO il loro id (per evitare ricorsione e loop)
     * @throws Exception Errore nel esecuzione della query sql
     */
    public List<Transaction> getTransactionsFromDB(Cashbook carrierCashbook) throws Exception{
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_TRANSACTIONS_FROM_CASHBOOKTRANSACTIONS, carrierCashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();

        //lista di pojo
        List<Transaction> transactionList = new ArrayList<Transaction>();
        while(rs.next()){
            //ogni volta trovo un nuovo pojo e lo inserisco nella lista
            Transaction t = new Transaction();
            //settare parametri
            t.setId(rs.getInt("transaction_id"));
            transactionList.add(t);
        }

        query.close();
        return transactionList;
    }

    @Override
    public void save(Cashbook cashbook) throws Exception {

    }

    @Override
    public void update(Cashbook cashbook, String[] params) throws Exception {

    }

    @Override
    public void delete(Cashbook cashbook) throws Exception {

    }

}
