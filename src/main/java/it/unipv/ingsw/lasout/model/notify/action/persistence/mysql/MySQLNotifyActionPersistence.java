package it.unipv.ingsw.lasout.model.notify.action.persistence.mysql;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MySQLNotifyActionPersistence implements INotifyActionPersistence {

    private static final String QUERY_LOAD_1 = "SELECT *" +
            " FROM \\'%s\\'" +
            " WHERE id = ?;";
    private static final String QUERY_DELETE_1 = "DELETE" +
            " FROM \\'%s\\'" +
            " WHERE id = ?;";
    private static final String QUERY_UPDATE = "UPDATE \\'%s\\'" +
            " SET %s WHERE id = ?;";
    private static final String QUERY_SAVE = "INSERT INTO \\'%s\\'" +
            " (%s, id) VALUES (%s)";

    protected String tableName;
    protected String update;
    protected String insert;

    @Override
    public void load(Notify notify) throws Exception {
        DBQuery query = DBQuery.Builder.create()
                .query(String.format(QUERY_LOAD_1, tableName))
                .params(notify.getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet resultSet = query.getResultSet();

        if(resultSet == null || !resultSet.next()) throw new RuntimeException(String.format("Could not find friend notify  with id '%s'", notify.getId()));

        innerLoad(resultSet, notify.getNotifyAction());

        query.close();
    }

    public abstract void innerLoad(ResultSet resultSet, INotifyAction iNotifyAction) throws SQLException;
                                   @Override
    public void delete(Notify notify) throws Exception {
           DBQuery dbQuery =  DBQuery.Builder.create()
                   .query(String.format(QUERY_DELETE_1, tableName))
                   .params(notify.getId())
                   .build();
           DatabaseUtil.getInstance().executeQuery(dbQuery);
           dbQuery.close();
    }

    public abstract void innerSave(DBQuery.Builder query, INotifyAction iNotifyAction) throws SQLException;

    @Override
    public void save(Notify notify) throws Exception {

        DBQuery.Builder dbQueryBuilder =  DBQuery.Builder.create().query(String.format(QUERY_UPDATE, tableName, update));

        innerSave(dbQueryBuilder, notify.getNotifyAction());

        dbQueryBuilder.addParam(notify.getId());

        DBQuery  dbQuery = dbQueryBuilder.build();
        DatabaseUtil.getInstance().executeQuery(dbQuery);

        if(dbQuery.getUpdateCount() == 0){
            dbQueryBuilder =  DBQuery.Builder.create();
            innerSave(dbQueryBuilder, notify.getNotifyAction());
            dbQueryBuilder.addParam(notify.getId());

            String questionMarks = "";
            for(int i =  0; i < dbQueryBuilder.getParams(); i++){
                questionMarks +=  "?, ";
            }
            questionMarks = questionMarks.substring(0, questionMarks.length()-2);
            String str = String.format(QUERY_SAVE, tableName, insert, questionMarks);
            dbQueryBuilder.query(str);
            DatabaseUtil.getInstance().executeQuery(dbQueryBuilder.build());
        }

        dbQuery.close();
    }
}
