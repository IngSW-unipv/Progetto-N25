package it.unipv.ingsw.lasout.model.cashbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.cashbook.exception.CashbookAlreadyExistingException;
import it.unipv.ingsw.lasout.model.transaction.ManualTransaction;
import it.unipv.ingsw.lasout.model.transaction.MySQLTransactionDao;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotDeleteAutomaticTransactionException;
import it.unipv.ingsw.lasout.model.user.User;

public class MySQLCashbookDao implements ICashbookDAO {
    /**
     * Istanza singola del CashbookDao (implementazione singleton)
     */
    private static MySQLCashbookDao instance = null;

    /**
     *
     * @return l'istanza singleton del CashbookDao
     */
    public static MySQLCashbookDao getInstance(){
        if (instance == null){
            instance= new MySQLCashbookDao();
        }
        return instance;
    }

    /**
     * Rendo il costruttore privato
     */
    public MySQLCashbookDao(){
        super();
    }


    private static final String GET_ALL_CASHBOOKS = "SELECT * FROM £cashbook£";
    private static final String GET_CASHBOOK_FROM_ID = "SELECT * FROM £cashbook£ WHERE id=?;";
    private static final String GET_TRANSACTIONS_FROM_CASHBOOKTRANSACTIONS = "SELECT * FROM £cashbooktransactions£ WHERE cashbook_id = ?;";
    private static final String GET_CASHBOOKS_FROM_TRANSACTION = "SELECT * FROM £cashbooktransactions£ WHERE transaction_id = ?;";
    private static final String GET_ALL_USER_CASHBOOKS = "SELECT * FROM £cashbook£ WHERE user_id = ?;";
    private static final String DELETE_FROM_CASHBOOKTRANSACTIONS = "DELETE FROM £cashbooktransactions£ WHERE cashbook_id = ?";
    private static final String DELETE_CASHBOOK_FROM_ID = "DELETE FROM £cashbook£ WHERE id = ?";
    private static final String INSERT_IN_CASHBOOKTRANSACTIONS = "INSERT INTO £cashbooktransactions£ (cashbook_id, transaction_id) VALUES(?,?)";
    private static final String INSERT_CASHBOOK_ID = "INSERT INTO £cashbook£ (id, user_id, name, type) VALUES(?,?,?,?)";
    private static final String INSERT_CASHBOOK_NOID = "INSERT INTO £cashbook£ (user_id, name, type) VALUES (?, ?, ?);";
    private static final String UPDATE_CASHBOOK = "UPDATE £cashbook£ SET name=? WHERE id=?";

    private Cashbook extractRawFromResultSet(ResultSet rs) throws SQLException {
        Cashbook savedCashbook = new Cashbook();
        savedCashbook.setId(rs.getInt("id"));
        savedCashbook.setName(rs.getString("name"));
        savedCashbook.setUser(new User(rs.getInt("user_id")));

        int type = rs.getInt("type");
        if (type == CashbookType.DEFAULT.getCode()){
            savedCashbook.setIsDefault(true);
        } else {
            savedCashbook.setIsDefault(false);
        }


        return savedCashbook;
    }

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

        Cashbook savedCashbook = extractRawFromResultSet(resultSet);

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

        List<Transaction> transactions = getCashbookTransactions(carrierCashbook);
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
            Cashbook cashbook = extractRawFromResultSet(rs);
            cashbook.setTransactionList(getCashbookTransactions(new Cashbook(rs.getInt("id"))));

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
    public List<Transaction> getCashbookTransactions(Cashbook carrierCashbook) throws Exception{
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_TRANSACTIONS_FROM_CASHBOOKTRANSACTIONS, carrierCashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();

        //lista di pojo
        List<Transaction> transactionList = new ArrayList<Transaction>();
        while(rs.next()){
            //ogni volta trovo un nuovo pojo e lo inserisco nella lista
            Transaction t;
            Transaction carrierTransaction = new ManualTransaction();
            carrierTransaction.setId(rs.getInt("transaction_id"));
            t = MySQLTransactionDao.getInstance().get(carrierTransaction);
            transactionList.add(t);
        }

        query.close();
        return transactionList;
    }

    public Cashbook getDefaultCashbook(User user) {
        List<Cashbook> cashbookList = null;
        try {
            cashbookList = getAllUserCashbooks(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(Cashbook c: cashbookList){
            if(c.isDefault())
                return c;
        }
        return null;
    }

    @Override
    public void addTransaction(Cashbook cashbook, Transaction transaction) throws Exception{
        MySQLTransactionDao.getInstance().save(transaction);

        DBQuery query = DatabaseUtil.getInstance().createQuery(INSERT_IN_CASHBOOKTRANSACTIONS, cashbook.getId(), transaction.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        query.close();
    }

    /**
     * Salvataggio di un cashbook nel database tenendo conto delle relazioni con gli oggetti collegati
     * @param cashbook carrier contentente solo l'id del cashbook da aggiungere dal database
     * @throws Exception errore nel esecuzione della query sql
     */
    @Override
    public void save(Cashbook cashbook) throws Exception {
        DBQuery query;
        
        try {
            int type;
            if(cashbook.isDefault()){
                type = CashbookType.DEFAULT.getCode();
            } else {
                type = CashbookType.GENERIC.getCode();
            }

            if (cashbook.getId() != 0) {
                query = DatabaseUtil.getInstance().createQuery(INSERT_CASHBOOK_ID, cashbook.getId(), cashbook.getUser().getId(), cashbook.getName(), type);
            } else {
                query = DatabaseUtil.getInstance().createGeneratedKeyQuery(INSERT_CASHBOOK_NOID, cashbook.getUser().getId(), cashbook.getName(), type);
            }
        } catch (Exception e) {
            throw new CashbookAlreadyExistingException(cashbook.getName());
        }
        DatabaseUtil.getInstance().executeQuery(query);

        //salvo id
        if (cashbook.getId() == 0) cashbook.setId((int)query.getKey());

        if(cashbook.getTransactionList()!=null){
            saveAssociation(cashbook);
        }

        query.close();
    }


    /**
     * Codice che implementa l'aggiunta della relazione N a N nel database
     * Attenzione: non esegue il salvataggio delle singole transazioni
     */
    private void saveAssociation(Cashbook cashbook) throws Exception {
        DBQuery query = null;
        for(Transaction t : cashbook.getTransactionList()){
            //salvo l'associazione nella tabella N a N
            query = DatabaseUtil.getInstance().createQuery(INSERT_IN_CASHBOOKTRANSACTIONS, cashbook.getId(), t.getId());
            DatabaseUtil.getInstance().executeQuery(query);
            // salvo le transazioni nella tabella
            // se get restituisce not found exception allora significa che posso crearla
            // altrimenti non faccio nulla e mantengo l'associazione
            try{
                Transaction n = MySQLTransactionDao.getInstance().get(t);
                if(!n.equals(t)){   //se la transazione è diversa da quella già presente fa un update
                    MySQLTransactionDao.getInstance().update(t);
                }
            } catch (RuntimeException transactionNotFound) {
                MySQLTransactionDao.getInstance().save(t);
            }
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

        if(cashbook.getTransactionList()!=null)
            deleteAssociation(cashbook);

        query.close();
    }

    /**
     * Codice che implementa l'eliminazione della relazione N a N nel database
     * e gestisce l'eliminazione delle transazioni
     */
    private void deleteAssociation(Cashbook cashbook) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELETE_FROM_CASHBOOKTRANSACTIONS, cashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        List<Transaction> transactions = cashbook.getTransactionList();
        for (Transaction t : transactions) {
            if (!isTransactionStillUsed(t)) {
                try {
                    MySQLTransactionDao.getInstance().delete(t);
                } catch (CannotDeleteAutomaticTransactionException e) {
                    continue;
                }
            }
        }

        query.close();
    }

    private boolean isTransactionStillUsed(Transaction transaction) throws Exception {
        DBQuery query = null;
        try {
            query = DatabaseUtil.getInstance().createQuery(GET_CASHBOOKS_FROM_TRANSACTION, transaction.getId());
            DatabaseUtil.getInstance().executeQuery(query);

            ResultSet rs = query.getResultSet();
            if (rs.next()) {
                return true; // la transazione è ancora usata in un altro cashbook
            }
            return false; // nessun altro cashbook la utilizza, posso eliminarla
        } finally {
            if (query != null) query.close();
        }
    }


    /**
     * Update dei dati riguardanti un cashbook senza la modifica delle transazioni ad esso collegate
     * @param cashbook carrier contentente solo l'id del cashbook da aggiornare
     * @throws Exception errore nell'esecuzione della query sql
     */
    public void update(Cashbook cashbook) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(UPDATE_CASHBOOK, cashbook.getName(), cashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        query.close();
    }

    @Override
    public List<Cashbook> getAllUserCashbooks(User carrierUser) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_USER_CASHBOOKS, carrierUser.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rs = query.getResultSet();
        if(rs==null)throw new Exception();

        //lista di pojo
        List<Cashbook> cashbooksList = new ArrayList<Cashbook>();
        while(rs.next()){
            //ogni volta trovo un nuovo pojo e lo inserisco nella lista
            //settare parametri
            Cashbook carrierCashbook = new Cashbook();
            carrierCashbook.setId(rs.getInt("id"));
            //riciclo funzione già esistente per completare
            carrierCashbook=get(carrierCashbook);

            cashbooksList.add(carrierCashbook);
        }

        query.close();
        return cashbooksList;
    }
}
