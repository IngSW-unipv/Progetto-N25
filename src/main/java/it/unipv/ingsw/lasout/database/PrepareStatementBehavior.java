package it.unipv.ingsw.lasout.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface PrepareStatementBehavior {


    PreparedStatement execute(Connection connection, String str) throws SQLException;
}
