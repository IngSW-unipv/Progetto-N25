package it.unipv.ingsw.lasout.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DefaultPrepareStatementBehavior implements PrepareStatementBehavior {



    @Override
    public PreparedStatement execute(Connection connection, String str) throws SQLException {
        return connection.prepareStatement(str);
    }
}
