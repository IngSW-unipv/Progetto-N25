package it.unipv.ingsw.lasout.model.cashbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.vault.Transaction;

public class CashbookDAO implements IDao<Cashbook>{

    private static final CashbookDAO INSTANCE = new CashbookDAO();
    public CashbookDAO getIstance(){
        return INSTANCE;
    }


    private static final String GET_ALL_CASHBOOK = "SELECT * FROM £cashbook£";
    private static final String GET_CASHBOOK_FROM_ID = "SELECT * FROM £cashbook£ WHERE user_id = ?;";
    private static final String GET_TRANSACTIONS_FROM_CASHBOOKTRANSACTIONS = "SELECT * FROM £cashbooktransactions£ WHERE cashbook_id = ?;";

    /**
     *
     * @param fictitiousCashbook ogetto contenente il solo identificatore dell'entità
     * @return un pojo group contenente solo le informazioni raw
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    public Cashbook getRaw(Cashbook fictitiousCashbook) throws SQLException {

        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_CASHBOOK_FROM_ID, fictitiousCashbook.getName());
        DatabaseUtil.getInstance().executeQuery(query);


        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException("Cashbook not found");


        Cashbook savedCashbook = new Cashbook();
        savedCashbook.setId(resultSet.getInt("id"));
        savedCashbook.setName(resultSet.getString("name"));


        return fictitiousCashbook;
    }

    /**
     *
     * @return Lista di tutti i cashbook presenti nel database
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    @Override
    public Cashbook get(Cashbook oggetto) throws Exception {
        return null;
    }

    /**
     *
     * @return Lista di tutti i cashbook presenti nel database
     * @throws Exception Errore nell'esecuzione della query o mancata connessione al db
     */
    @Override
    public List<Cashbook> getAll() throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_ALL_CASHBOOK);

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        List<Cashbook> cashbooksList = new ArrayList<Cashbook>();
        while(rs.next()){
            Cashbook cashbook = new Cashbook();
            cashbook.setId(rs.getInt("id"));
            cashbook.setName(rs.getString("name"));
            //cashbook.setTransactionList(transactions(new Transaction(rs.getInt("id"))));
            cashbooksList.add(cashbook);
        }

        query.close();
        return List.of();
    }

    /**
     * UserGroupDAO: retituisce la lista bi user di un gruppo dalla relazione usergroup (many to many)
     * @param cashbook carry contentente solo l'id del gruppo di cui si vogliono i membri
     * @return Lista di user con solo il loro id (per evitare ricorsione e loop)
     * @throws Exception Errore nel esecuzione della query sql
     */
    public List<Transaction> transactions(Cashbook cashbook) throws Exception{
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_TRANSACTIONS_FROM_CASHBOOKTRANSACTIONS, cashbook.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        //lista di pojo
        List<Transaction> transactionList = new ArrayList<Transaction>();
        while(rs.next()){
            //ogni volta un nuovo pojo
            Transaction t = new Transaction();
            //settare parametri


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
