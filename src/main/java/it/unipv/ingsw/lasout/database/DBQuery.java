package it.unipv.ingsw.lasout.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBQuery {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private PrepareStatementBehavior prepareStatementBehavior;
    private ResultSet resultSet;
    private String query;
    private Object[] params;


    public DBQuery(String query, Object... params) {
        setQuery(query);
        if(params != null) this.params = params;
        this.prepareStatementBehavior = new DefaultPrepareStatementBehavior();

    }

    public DBQuery(String query, List<Object> params) {
        setQuery(query);
        if(params != null) this.params = params.toArray();
        this.prepareStatementBehavior = new DefaultPrepareStatementBehavior();
    }

    /**
     * Chiude solo i dati, mantenedo la  connessione attiva
     * Utile per utilizzare la stessa connessione più volte
     * @throws SQLException
     */
    public void flush() throws SQLException {

        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(resultSet != null){
            resultSet.close();
        }

    }

    /**
     * Chiude sia la connessione che i dati
     * @throws SQLException
     */
    public void close() throws SQLException {
        if(connection != null){
            connection.close();
        }
        flush();
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query.replace("\\'", "`").replace("$", "`").replace("£", "`");
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object... params){
        this.params = params;
    }


    public PrepareStatementBehavior getPrepareStatementBehavior() {
        return prepareStatementBehavior;
    }

    public void setPrepareStatementBehavior(PrepareStatementBehavior prepareStatementBehavior) {
        this.prepareStatementBehavior = prepareStatementBehavior;
    }

    public PreparedStatement prepareStatement() throws SQLException {
        this.preparedStatement =  prepareStatementBehavior.execute(connection, query);
        return this.preparedStatement;
    }

    /**
     * In caso di insert con auto increment della primy key ritorna il valore
     * della primary key asegnata in utomatico dal database
     * @return long id generato dal database
     * @throws Exception il database non ha generato chiavi (non e che nel carry gliela avevi gia messa?)
     */
    public long getKey() throws Exception {
        ResultSet r= getPreparedStatement().getGeneratedKeys();
        if(r==null || !r.next()) throw new Exception();
        return r.getLong(1);
    }

    /** per ottenere quanti dati sono stati modificati da un'update
     * @return il numero di row modificate
     * @throws SQLException se c'è stato un errore
     */
    public int getUpdateCount() throws SQLException {
        return getPreparedStatement().getUpdateCount();
    }



    public static class Builder {

        private String queryString;
        private PrepareStatementBehavior prepareStatementBehavior;
        private List<Object> params;

        private Builder(){
            this.prepareStatementBehavior = new DefaultPrepareStatementBehavior();
        }

        public static Builder create(){
            return new Builder();
        }

        public Builder query(String queryStr){
            this.queryString = queryStr;
            return this;
        }

        public Builder getGeneratedKeys(){
            this.prepareStatementBehavior = new ReturnGeneratedKeyPrepareStatementBehavior();
            return this;
        }
        public Builder params(Object... params){
            this.params = new ArrayList<>();
            this.params.addAll(Arrays.asList(params));
            return this;
        }
        public Builder addParam(Long id) {
            if(params == null) this.params  = new ArrayList<>();
            this.params.add(id);
            return this;
        }

        public DBQuery build(){

            DBQuery dbQuery = new DBQuery(queryString, params);

            dbQuery.setPrepareStatementBehavior(prepareStatementBehavior);
            return dbQuery;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "queryString='" + queryString + '\'' +
                    ", prepareStatementBehavior=" + prepareStatementBehavior +
                    ", params=" + params +
                    '}';
        }

        public int getParams() {
            return params == null ? 0 : params.size();
        }
    }

    @Override
    public String toString() {
        return "DBQuery{" +
                "connection=" + connection +
                ", preparedStatement=" + preparedStatement +
                ", prepareStatementBehavior=" + prepareStatementBehavior +
                ", resultSet=" + resultSet +
                ", query='" + query + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
