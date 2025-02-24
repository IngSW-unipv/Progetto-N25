package it.unipv.ingsw.lasout.model.transaction;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotEditTransactionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RdbTransactionDao implements ITransactionDAO{
    /**
     * Istanza singola del GroupDao (implementazione singleton)
     */
    private static RdbTransactionDao instance = null;

    /**
     *
     * @return l'istanza singleton del GroupDao
     */
    public static RdbTransactionDao getInstance(){
        if (instance == null){
            instance= new RdbTransactionDao();
        }
        return instance;
    }

    /**
     * Rendo il costruttore privato
     */
    private RdbTransactionDao(){
        super();
    }

    private static final String GET_ALL_TRANSACTIONS = "SELECT * FROM £transactions£";
    private static final String GET_TRANSACTION_FROM_ID = "SELECT * FROM £transactions£ WHERE id=?;";
    private static final String UPDATE_TRANSACTION = "UPDATE £transactions£ SET amount=?, date=?, category=?, note=? WHERE id=?";
    private static final String DELETE_FROM_CASHBOOKTRANSACTIONS = "DELETE FROM £cashbooktransactions£ WHERE transaction_id = ?";
    private static final String DELETE_TRANSACTION_FROM_ID = "DELETE FROM £transactions£ WHERE id = ?";
    private static final String INSERT_TRANSACTION_ID = "INSERT INTO £transactions£ (id, type, amount, date, category, note) VALUES(?,?,?,?,?,?)";
    private static final String INSERT_TRANSACTION_NOID = "INSERT INTO £transactions£ (type, amount, date, category, note) VALUES (?, ?, ?, ?, ?);";

    /***
     *
     * @param resultSet ovvero il result set passato dai vari metodi nel dao
     * @return transazione del tipo corretto
     * @throws SQLException nel caso in cui il resultSet non disponga delle colonne corrette
     */
    private Transaction extractTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        Transaction savedTransaction = null;
        TransactionType type = TransactionType.fromCode(resultSet.getInt("type"));

        if(type==TransactionType.AUTOMATIC){
            savedTransaction = new AutomaticTransaction();
        } else {
            savedTransaction = new ManualTransaction();
        }
        savedTransaction.setId(resultSet.getInt("id"));
        savedTransaction.setAmount(resultSet.getDouble("amount"));
        savedTransaction.setDate(resultSet.getString("date"));
        savedTransaction.setCategory(resultSet.getString("category"));
        savedTransaction.setNotes(resultSet.getString("note"));

        return savedTransaction;
    }

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

        Transaction savedTransaction = extractTransactionFromResultSet(resultSet);

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
            Transaction transaction = extractTransactionFromResultSet(rs);

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

        // selezione del tipo di transazione prima di inserimento
        TransactionType type;
        try {
            ModifiableTransaction t = (ModifiableTransaction) transaction;
            type = TransactionType.MANUAL;
        } catch (Exception e) {
            type = TransactionType.AUTOMATIC;
        }

        if(transaction.getId()!=0){
            query = DatabaseUtil.getInstance().createQuery(INSERT_TRANSACTION_ID, transaction.getId(), type.getCode(), transaction.getAmount(), transaction.getDate(), transaction.getCategory(), transaction.getNotes());
        }
        else{
            query = DatabaseUtil.getInstance().createQuery(INSERT_TRANSACTION_NOID, type.getCode(), transaction.getAmount(), transaction.getDate(), transaction.getCategory(), transaction.getNotes());
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
        DBQuery query = null;

        // lancerà un errore di casting, perciò non posso modificare la transazione
        try{
            ModifiableTransaction t = (ModifiableTransaction) transaction;
        } catch (Exception e) {
            throw new CannotEditTransactionException();
        }

        try {
            query = DatabaseUtil.getInstance().createQuery(UPDATE_TRANSACTION,
                    transaction.getAmount(),
                    transaction.getDate(),
                    transaction.getCategory(),
                    transaction.getNotes(),
                    transaction.getId()
            );
            DatabaseUtil.getInstance().executeQuery(query);
        } finally {
            if (query != null) query.close();
        }
    }


}
