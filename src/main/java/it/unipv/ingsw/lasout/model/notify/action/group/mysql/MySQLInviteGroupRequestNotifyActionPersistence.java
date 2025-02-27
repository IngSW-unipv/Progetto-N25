package it.unipv.ingsw.lasout.model.notify.action.group.mysql;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.IGroupDao;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.group.InviteGroupRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.mysql.MySQLNotifyActionPersistence;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLInviteGroupRequestNotifyActionPersistence  extends MySQLNotifyActionPersistence {

    private IUserDAO iUserDAO;
    private IGroupDao iGroupDAO;

    public MySQLInviteGroupRequestNotifyActionPersistence() {
        this.tableName = "groupinvitenotify";
        this.update  = "to_user_id = ?, from_group_id = ?";
        this.insert  = "to_user_id, from_group_id";

        this.iGroupDAO  = DaoFactory.getGroupDAO();
        this.iUserDAO  = DaoFactory.getUserDAO();
    }
    @Override
    public void innerLoad(ResultSet resultSet, INotifyAction iNotifyAction) throws SQLException {



        try {
            InviteGroupRequestNotifyAction inviteGroupRequestNotifyAction = (InviteGroupRequestNotifyAction) iNotifyAction;

            User toUser = iUserDAO.getRaw(new User(resultSet.getLong("to_user_id")));
            Group fromGroup = iGroupDAO.getRaw(new Group(resultSet.getLong("from_group_id")));

            inviteGroupRequestNotifyAction.setGroup(fromGroup);
            inviteGroupRequestNotifyAction.setUser(toUser);

        } catch (Exception e) {

        }

    }

    @Override
    public void innerSave(DBQuery.Builder query, INotifyAction iNotifyAction) throws SQLException {

        try{
            InviteGroupRequestNotifyAction friendRequestNotifyAction = (InviteGroupRequestNotifyAction) iNotifyAction;
            query.params(friendRequestNotifyAction.getUser().getId(),  friendRequestNotifyAction.getGroup().getId());
        }catch (ClassCastException e){

        }

    }
}
