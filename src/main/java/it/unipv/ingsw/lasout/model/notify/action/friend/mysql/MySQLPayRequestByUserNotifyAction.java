package it.unipv.ingsw.lasout.model.notify.action.friend.mysql;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.PayRequestByUserNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.mysql.MySQLNotifyActionPersistence;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPayRequestByUserNotifyAction extends MySQLNotifyActionPersistence {

    private IUserDAO userDAO;

    public MySQLPayRequestByUserNotifyAction() {
        this.tableName = "payrequestbyusernotify";
        this.update  = "to_user_id = ?, from_user_id = ?, amount = ?";
        this.insert  = "to_user_id, from_user_id, amount";

        userDAO = DaoFactory.getUserDAO();
    }


    @Override
    public void innerLoad(ResultSet resultSet, INotifyAction iNotifyAction) throws SQLException {

        try {
            PayRequestByUserNotifyAction request = (PayRequestByUserNotifyAction) iNotifyAction;
            User toUser = userDAO.get(new User(resultSet.getInt("to_user_id")));
            User fromUser = userDAO.get(new User(resultSet.getInt("from_user_id")));
            double amount = resultSet.getDouble("amount");

            request.setUser(toUser);
            request.setFrom(fromUser);
            request.setAmount(amount);

        } catch (Exception e) {

        }

    }

    @Override
    public void innerSave(DBQuery.Builder query, INotifyAction iNotifyAction) throws SQLException {

        try{
            PayRequestByUserNotifyAction request = (PayRequestByUserNotifyAction) iNotifyAction;
            query.params(request.getUser().getId(),  request.getFrom().getId(),  request.getAmount());
        }catch (ClassCastException e){

        }

    }


}
