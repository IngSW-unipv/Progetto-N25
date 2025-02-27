package it.unipv.ingsw.lasout.model.notify.action.friend.mysql;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.mysql.MySQLNotifyActionPersistence;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLFriendRequestNotifyActionPersistence extends MySQLNotifyActionPersistence {

    public MySQLFriendRequestNotifyActionPersistence() {
        this.tableName = "friendnotify";
        this.update  = "to_user_id = ?, from_user_id = ?";
        this.insert  = "to_user_id, from_user_id";
    }

    @Override
    public void innerLoad(ResultSet resultSet, INotifyAction iNotifyAction) throws SQLException {
        try{
            User fromID = UserDAO.getInstance().getRaw(new User(resultSet.getInt("from_user_id")));
            User toID = UserDAO.getInstance().getRaw(new User(resultSet.getInt("to_user_id")));
            FriendRequestNotifyAction notifyAction = (FriendRequestNotifyAction) iNotifyAction;
            notifyAction.setFrom(fromID);
            notifyAction.setTo(toID);
        }catch (ClassCastException e){

        }
    }

    @Override
    public void innerSave(DBQuery.Builder query, INotifyAction iNotifyAction) throws SQLException {
        try{
            FriendRequestNotifyAction  friendRequestNotifyAction = (FriendRequestNotifyAction) iNotifyAction;
            query.params(friendRequestNotifyAction.getTo().getId(),  friendRequestNotifyAction.getFrom().getId());
        }catch (ClassCastException e){

        }

    }
}
