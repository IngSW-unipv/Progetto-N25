package it.unipv.ingsw.lasout.database;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseUtil {


    private static final String MANY_TO_MANY = "";

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

    public void prepare() throws IOException {
        Properties prop = new Properties();
        prop.load(DatabaseUtil.class.getResourceAsStream("/app.properties"));

        dbName = prop.getProperty("dbname");
        host = prop.getProperty("host");
        port = prop.getProperty("port");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
    }
    public void  initialize() throws SQLException {

        InputStream inputStream = DatabaseUtil.class.getResourceAsStream("/initsql.txt");
        if(inputStream == null) throw new RuntimeException("Could not find initsql.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuffer buffer = new StringBuffer();
        bufferedReader.lines().forEach(buffer::append);

        DBQuery dbQuery = new DBQuery("");
        Arrays.stream(buffer.toString().split(";")).forEach(line->{
            dbQuery.setQuery(line+";");
            try {
                executeQuery(dbQuery);
            } catch (SQLException e) {
                LOGGER.severe("Error executing query: " + line + "error: " + e.getMessage());
            }
        });

    }


    /**
     * Util per eseguire qualsiasi tipo di DBQuery
     * salva il risultato in resultSet, posso utilizzare la stessa DBQuery
     * con stessa  connessione per renderlo più efficiente, il metodo gestisce tutto.
     *
     * @param query può essere una nuova query (senza connessione  etc..)
     *              o una query con una  connessione già aperta
     * @throws SQLException
     */
    public void executeQuery(DBQuery query) throws SQLException {

        try{

            if(query.getConnection() == null || query.getConnection().isClosed()){
                Connection con = DriverManager.getConnection(String.format(CONNECTION_URL, host, port, dbName),  username, password);
                query.setConnection(con);
            }else{
                query.flush();
            }

            PreparedStatement pS = query.getConnection().prepareStatement(query.getQuery());
            for(int i = 0; i <  query.getParams().length; i++){
                pS.setObject(i + 1, query.getParams()[i] != null ?  query.getParams()[i].toString() : null);
            }

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
        s = s.replace("\\'", "`");
        s = s.replace("$", "`");
        s = s.replace("£", "`");
        return new DBQuery(s, params);
    }
}
