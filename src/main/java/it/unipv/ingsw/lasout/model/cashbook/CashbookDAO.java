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
            "WHERE id = ?;";
    
            private static final String QUERY_GET_2 =
            "SELECT * " +
            "FROM `cashbook`" +
            "WHERE id = ?;";

    public Cashbook getRaw(Cashbook cashbook) throws SQLException {

        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GET_1, cashbook.getId());
        DatabaseUtil.getInstance().executeQuery(query);


        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException("Cashbook not found");


        Cashbook savedUser = new Cashbook();
        savedUser.setName(resultSet.getInt("name"));
        savedUser.setTransactionList(resultSet.getString(""));

        return user;
    }



}
