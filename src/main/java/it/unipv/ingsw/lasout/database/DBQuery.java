package it.unipv.ingsw.lasout.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBQuery {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private PrepareStatementBehavior prepareStatementBehavior;
    private ResultSet resultSet;
    private String query;
    private Object[] params;


    public DBQuery(String query, Object... params) {
        this.query = query;
        this.params = params;
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
        this.query = query;
    }

    public Object[] getParams() {
        return params;
    }

    public   void setParams(Object... params){
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


    public static class Builder {

        private String queryString;
        private PrepareStatementBehavior prepareStatementBehavior;
        private Object[] params;

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
            this.params = params;
            return this;
        }

        public DBQuery build(){

            queryString = queryString.replace("\\'", "`");
            queryString = queryString.replace("$", "`");
            queryString = queryString.replace("£", "`");
            DBQuery dbQuery = new DBQuery(queryString, params);

            dbQuery.setPrepareStatementBehavior(prepareStatementBehavior);
            return dbQuery;
        }

    }

}
