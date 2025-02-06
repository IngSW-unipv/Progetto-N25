package it.unipv.ingsw.lasout.model.cashbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;

public class CashbookDAO implements IDao<Cashbook>{

    private static final CashbookDAO INSTANCE = new CashbookDAO();
    public CashbookDAO getIstance(){
        return INSTANCE;
    }


    private static final String QUERY_GET_1 =
        "SELECT * " +
        "FROM `cashbook`" +
        "WHERE user_id = ?;";

    private static final String QUERY_TRANSACTIONS_OF_1 =
        "SELECT * " +
        "FROM `transactions`" +
        "WHERE cashbook_id = ?;";


    public Cashbook getRaw(Cashbook cashbook) throws SQLException {

        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GET_1, cashbook.getName());
        DatabaseUtil.getInstance().executeQuery(query);


        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException("Cashbook not found");


        Cashbook savedCashbook = new Cashbook();
        savedCashbook.setId(resultSet.getInt("id"));
        savedCashbook.setName(resultSet.getString("name"));



        return cashbook;
    }

    @Override
    public Cashbook get(Cashbook oggetto) throws Exception {
        return null;
    }

    @Override
    public List<Cashbook> getAll() throws Exception {
        return List.of();
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
