package it.unipv.ingsw.lasout.model.notify.action.friend.persistance;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistance.INotifyActionPersistence;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import java.sql.ResultSet;

public class MySQLFriendRequestNotifyActionPersistence implements INotifyActionPersistence {

    private static final String QUERY_LOAD_1 = "SELECT *" +
            " FROM \\'friendnotify\\'" +
            " WHERE id = ?;";
    private static final String QUERY_DELETE_1 = "DELETE" +
            " FROM \\'friendnotify\\'" +
            " WHERE id = ?;";
    private static final String QUERY_UPDATE = "UPDATE \\'friendnotify\\'" +
            " SET to_user_id = ?, from_user_id = ? WHERE id = ?;";
    private static final String QUERY_SAVE = "INSERT INTO \\'friendnotify\\'" +
            " (id, to_user_id, from_user_id) VALUES (?, ?, ?)";



    @Override
    public void load(Notify notify) throws Exception {

        DBQuery query = DBQuery.Builder.create()
                .query(QUERY_LOAD_1)
                .params(notify.getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet resultSet = query.getResultSet();

        if(resultSet == null || !resultSet.next()) throw new RuntimeException(String.format("Could not find friend notify  with id '%s'", notify.getId()));

        User fromID = UserDAO.getInstance().getRaw(new User(resultSet.getInt("from_user_id")));
        User toID = UserDAO.getInstance().getRaw(new User(resultSet.getInt("to_user_id")));


        try{
            FriendRequestNotifyAction notifyAction = (FriendRequestNotifyAction) notify.getNotifyAction();
            notifyAction.setFrom(fromID);
            notifyAction.setTo(toID);
        }catch (ClassCastException e){
        }


        query.close();
    }

    @Override
    public void delete(Notify notify) throws Exception {
        DBQuery dbQuery =  DBQuery.Builder.create()
                .query(QUERY_DELETE_1)
                .params(notify.getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(dbQuery);
        dbQuery.close();
    }

    @Override
    public void save(Notify notify) throws Exception {

        FriendRequestNotifyAction friendRequestNotifyAction = (FriendRequestNotifyAction) notify.getNotifyAction();

        DBQuery dbQuery =  DBQuery.Builder.create()
                .query(QUERY_UPDATE)
                .params(friendRequestNotifyAction.getTo().getId(),  friendRequestNotifyAction.getFrom().getId(), friendRequestNotifyAction.getNotify().getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(dbQuery);
        if(dbQuery.getUpdateCount() == 0){
            dbQuery.setQuery(QUERY_SAVE);
            dbQuery.setParams(friendRequestNotifyAction.getNotify().getId(), friendRequestNotifyAction.getTo().getId(),  friendRequestNotifyAction.getFrom().getId());
            DatabaseUtil.getInstance().executeQuery(dbQuery);
        }

        dbQuery.close();
    }
}
