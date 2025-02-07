package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import java.sql.ResultSet;

public class AcceptFriendRequestNotifyAction implements INotifyAction {


    private User from;
    private User to;

    @Override
    public void accept() {

    }

    @Override
    public void build() {

    }

    @Override
    public void load(Notify notify) throws Exception{
        DBQuery query = DBQuery.Builder.create()
                .query("SELECT *" +
                        "FROM \\'friendnotify\\' " +
                        "WHERE id = ? AND to_user_id = ?;")
                .params(notify.getId(), notify.getUserID())
                .build();
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException(String.format("Could not find friend notify  with id '%s' and user_id '%s'", notify.getId(), notify.getUserID()));

        User fromID = UserDAO.getInstance().get(new User(resultSet.getInt("from_user_id")));
        User toID = UserDAO.getInstance().get(new User(resultSet.getInt("to_user_id")));

        this.from = fromID;
        this.to = toID;
    }

    @Override
    public String type() {
        return "notifyfriendrequest";
    }

    @Override
    public String toString() {
        return "AcceptFriendRequestNotifyAction{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
