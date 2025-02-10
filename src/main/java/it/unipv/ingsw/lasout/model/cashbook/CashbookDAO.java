package it.unipv.ingsw.lasout.model.cashbook;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.transaction.Transaction;

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
    private static final String DELETE_FROM_CASHBOOKTRANSACTIONS = "DELETE FROM £cashbooktransactions£ WHERE cashbook_id = ?";
    private static final String DELETE_CASHBOOK_FROM_ID = "DELETE FROM £cashbook£ WHERE id = ?";
    private static final String INSERT_IN_CASHBOOKTRANSACTIONS = "INSERT INTO £cashbooktransactions£ (cashbook_id, transaction_id) VALUES(?,?)";
    private static final String INSERT_CASHBOOK_ID = "INSERT INTO £cashbook£ (id, user_id, name) VALUES(?,?,?)";
    private static final String INSERT_CASHBOOK_NOID = "INSERT INTO £cashbook£ (id, user_id, name) VALUES (?, ?, ?);";

    /**
     * Voglio ottenere un CashBook dal DB solo tramite il suo ID
     * @param carrierCashbook ogetto contenente il solo identificatore dell'entità
     * @return un Cashbook contenente solo le informazioni raw, ovvero privo delle sue transazioni
     * @throws Exception Errore nell'esecuzione della query o mancata connessione al db
     */
    @Override
    public Cashbook getRaw(Cashbook carrierCashbook) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_CASHBOOK_FROM_ID, carrierCashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) {
            throw new RuntimeException("Cashbook not found");
        }

        Cashbook savedCashbook = new Cashbook();
        savedCashbook.setId(resultSet.getInt("id"));
        savedCashbook.setName(resultSet.getString("name"));
        savedCashbook.setUserId(resultSet.getInt("user_id"));

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
        Cashbook savedCashbook = getRaw(carrierCashbook);

        List<Transaction> transactions = getTransactionsFromDB(carrierCashbook);
        savedCashbook.setTransactionList(transactions);

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

    /**
     * Salvataggio di un cashbook nel database tenendo conto delle relazioni con gli oggetti collegati
     * @param cashbook carrier contentente solo l'id del cashbook da aggiungere dal database
     * @throws Exception errore nel esecuzione della query sql
     */
    @Override
    public void save(Cashbook cashbook) throws Exception {
        DBQuery query;

        if(cashbook.getId()!=0){
            query = DatabaseUtil.getInstance().createQuery(INSERT_CASHBOOK_ID, cashbook.getId(), cashbook.getUserId(), cashbook.getName());
        }
        else{
            query = DatabaseUtil.getInstance().createQuery(INSERT_CASHBOOK_NOID, cashbook.getUserId(), cashbook.getName());
        }

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if(rs!=null)throw new Exception();


        //INSERT nella tabella usergroup
        saveAssociation(cashbook);
        query.close();
    }

    /**
     * Codice che implementa l'aggiunta della relazione N a N nel database
     */
    private void saveAssociation(Cashbook cashbook) throws Exception {
        DBQuery query = null;
        for(Transaction t : cashbook.getTransactionList()){
            query = DatabaseUtil.getInstance().createQuery(INSERT_IN_CASHBOOKTRANSACTIONS, cashbook.getId(), t.getId());
            DatabaseUtil.getInstance().executeQuery(query);
            ResultSet rs = query.getResultSet();

            if(rs!=null)throw new Exception();
        }
        if(query!=null) query.close();
    }


    /**
     * Eliminazione di un cashbook dal database per aggiornamento o eliminazione dei
     * dati tenendo conto anche delle relazioni ed eliminandole di conseguenza
     * @param cashbook carrier contentente solo l'id del cashbook da eliminare dal database
     * @throws Exception errore nel esecuzione della query sql
     */
    @Override
    public void delete(Cashbook cashbook) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELETE_CASHBOOK_FROM_ID, cashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();
        if(rs!=null)throw new Exception();

        deleteAssociation(cashbook);
        query.close();
    }

    /**
     * Codice che implementa l'eliminazione della relazione N a N nel database
     */
    private void deleteAssociation(Cashbook cashbook) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELETE_FROM_CASHBOOKTRANSACTIONS, cashbook.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if(rs!=null)throw new Exception();
        query.close();
    }

    /**
     * Update dei dati riguardanti un cashbook con conseguente modifica delle relazioni ad esso collegate
     * @param cashbook carrier contentente solo l'id del cashbook da aggiornare
     * @throws Exception errore nell'esecuzione della query sql
     */
    public void update(Cashbook cashbook) throws Exception {
        delete(cashbook);
        save(cashbook);
    }

}
