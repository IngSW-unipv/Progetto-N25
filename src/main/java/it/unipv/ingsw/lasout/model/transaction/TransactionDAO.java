package it.unipv.ingsw.lasout.model.transaction;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements ITransactionDAO{
    /**
     * Istanza singola del GroupDao (implementazione singleton)
     */
    private static TransactionDAO instance = null;

    /**
     *
     * @return l'istanza singleton del GroupDao
     */
    public static TransactionDAO getInstance(){
        if (instance == null){
            instance= new TransactionDAO();
        }
        return instance;
    }

    /**
     * Rendo il costruttore privato
     */
    private TransactionDAO(){
        super();
    }

    private static final String GET_ALL_TRANSACTIONS = "SELECT * FROM £transactions£";
    private static final String GET_TRANSACTION_FROM_ID = "SELECT * FROM £transactions£ WHERE id=?;";
    private static final String GET_TRANSACTIONS_FROM_CASHBOOKTRANSACTIONS = "SELECT * FROM £cashbooktransactions£ WHERE transaction_id = ?;";
    private static final String DELETE_FROM_CASHBOOKTRANSACTIONS = "DELETE FROM £cashbooktransactions£ WHERE transaction_id = ?";
    private static final String DELETE_TRANSACTION_FROM_ID = "DELETE FROM £transactions£ WHERE id = ?";
    private static final String INSERT_IN_CASHBOOKTRANSACTIONS = "INSERT INTO £cashbooktransactions£ (cashbook_id, transaction_id) VALUES(?,?)";
    private static final String INSERT_TRANSACTION_ID = "INSERT INTO £transactions£ (id, type, amount, date, category, note) VALUES(?,?,?,?,?,?)";
    private static final String INSERT_TRANSACTION_NOID = "INSERT INTO £transactions£ (type, amount, date, category, note) VALUES (?, ?, ?, ?, ?);";

    /**
     * Voglio ottenere un CashBook dal DB solo tramite il suo ID
     * @param carrierTransaction ogetto contenente il solo identificatore dell'entità
     * @return un Cashbook contenente solo le informazioni raw, ovvero privo delle sue transazioni
     * @throws Exception Errore nell'esecuzione della query o mancata connessione al db
     */
    @Override
    public Transaction getRaw(Transaction carrierTransaction) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_TRANSACTION_FROM_ID, carrierTransaction.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) {
            throw new RuntimeException("Transaction not found");
        }

        Transaction savedTransaction = new Transaction();
        savedTransaction.setId(resultSet.getInt("id"));
        savedTransaction.setType(resultSet.getInt("type"));
        savedTransaction.setAmount(resultSet.getDouble("amount"));
        savedTransaction.setDate(resultSet.getString("date"));
        savedTransaction.setCategory(resultSet.getString("category"));
        savedTransaction.setNotes(resultSet.getString("note"));

        query.close();
        return savedTransaction;
    }

    /**
     * Ricerca di un cashbook dato solamente il suo ID, restituisce il cashbook completo
     * @param carrierTransaction ovvero un cashbook con solo l'ID
     * @return Cashbook cercato
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    @Override
    public Transaction get(Transaction carrierTransaction) throws Exception {
        return getRaw(carrierTransaction);
    }

    /**
     * Voglio una funzione che restituisca tutti i CashBooks presenti nel database
     * @return Lista di tutti i CashBooks presenti nel database
     * @throws Exception Errore nell'esecuzione della query o mancata connessione al db
     */
    @Override
    public List<Transaction> getAll() throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_TRANSACTIONS);
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();
        List<Transaction> transactionsList = new ArrayList<Transaction>();

        while(rs.next()){
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setType(rs.getInt("type"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDate(rs.getString("date"));
            transaction.setCategory(rs.getString("category"));
            transaction.setNotes(rs.getString("note"));

            transactionsList.add(transaction);
        }

        query.close();
        return transactionsList;
    }

    /**
     * Salvataggio di un cashbook nel database tenendo conto delle relazioni con gli oggetti collegati
     * @param transaction carrier contentente solo l'id del cashbook da aggiungere dal database
     * @throws Exception errore nel esecuzione della query sql
     */
    @Override
    public void save(Transaction transaction) throws Exception {
        DBQuery query;

        if(transaction.getId()!=0){
            query = DatabaseUtil.getInstance().createQuery(INSERT_TRANSACTION_ID, transaction.getId(), transaction.getType(), transaction.getAmount(), transaction.getDate(), transaction.getCategory(), transaction.getNotes());
        }
        else{
            query = DatabaseUtil.getInstance().createQuery(INSERT_TRANSACTION_NOID, transaction.getType(), transaction.getAmount(), transaction.getDate(), transaction.getCategory(), transaction.getNotes());
        }

        DatabaseUtil.getInstance().executeQuery(query);

        query.close();
    }


    /**
     * Eliminazione di un cashbook dal database per aggiornamento o eliminazione dei
     * dati tenendo conto anche delle relazioni ed eliminandole di conseguenza
     * @param transaction carrier contentente solo l'id del cashbook da eliminare dal database
     * @throws Exception errore nel esecuzione della query sql
     */
    @Override
    public void delete(Transaction transaction) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELETE_TRANSACTION_FROM_ID, transaction.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        deleteAssociation(transaction);
        query.close();
    }

    /**
     * Codice che implementa l'eliminazione della relazione N a N nel database
     */
    private void deleteAssociation(Transaction transaction) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELETE_FROM_CASHBOOKTRANSACTIONS, transaction.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        query.close();
    }

    /**
     * Update dei dati riguardanti un cashbook con conseguente modifica delle relazioni ad esso collegate
     * @param transaction carrier contentente solo l'id del cashbook da aggiornare
     * @throws Exception errore nell'esecuzione della query sql
     */
    public void update(Transaction transaction) throws Exception {
        delete(transaction);
        save(transaction);
    }


}
