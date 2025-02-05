package it.unipv.ingsw.lasout.database;

import java.sql.*;

public class ReturnGeneratedKeyPrepareStatementBehavior implements PrepareStatementBehavior {


    @Override
    public PreparedStatement execute(Connection connection, String str) throws SQLException {
        return connection.prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
    }
}
