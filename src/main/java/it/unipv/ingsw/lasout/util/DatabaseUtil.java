package it.unipv.ingsw.lasout.util;
import it.unipv.ingsw.lasout.dao.DBQuery;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseUtil {


    private static final String CONNECTION_URL = "jdbc:mysql://%s:%s/%s";
    private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());


    private static DatabaseUtil INSTANCE = new DatabaseUtil();
    public static DatabaseUtil getInstance() {return INSTANCE;}


    private String host;
    private String port;
    private String username;
    private String password;
    private String dbName;

    private DatabaseUtil() {


    }

    public void  initialize() throws IOException, SQLException {


        Properties prop = new Properties();
        prop.load(DatabaseUtil.class.getResourceAsStream("/app.properties"));

        dbName = prop.getProperty("dbname");
        host = prop.getProperty("host");
        port = prop.getProperty("port");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        try(Connection con = DriverManager.getConnection(String.format(CONNECTION_URL, host, port, dbName),  username, password)){

            LOGGER.info("Connection established successfully !");

        }catch (SQLException e){
            throw new SQLException("Could not connect to database: "  + e);
        }


    }


    public void executeQuery(DBQuery query) throws SQLException {

        try{

            Connection con = DriverManager.getConnection(String.format(CONNECTION_URL, host, port, dbName),  username, password);
            PreparedStatement pS = con.prepareStatement(query.getQuery());
            for(int i = 0; i <  query.getParams().length; i++){
                pS.setObject(i + 1, query.getParams()[i] != null ?  query.getParams()[i].toString() : null);
            }

            query.setConnection(con);
            query.setPreparedStatement(pS);

            boolean hasResults = pS.execute();
            if(hasResults){
                query.setResultSet(pS.getResultSet());
            }


        }catch (SQLException e){
            throw new SQLException("Fucking error occurred: "  + e);
        }

    }


    public DBQuery createQuery(String s, Object... params) {
        return new DBQuery(s, params);
    }
}
