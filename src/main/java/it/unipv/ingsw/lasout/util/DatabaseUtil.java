package it.unipv.ingsw.lasout.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        String dbName = prop.getProperty("dbname");
        String host = prop.getProperty("host");
        String port = prop.getProperty("port");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        try(Connection con = DriverManager.getConnection(String.format(CONNECTION_URL, host, port, dbName),  username, password)){

            LOGGER.info("Connection established successfully !");

        }catch (SQLException e){
            throw new SQLException("Could not connect to database: "  + e);
        }


    }











}
