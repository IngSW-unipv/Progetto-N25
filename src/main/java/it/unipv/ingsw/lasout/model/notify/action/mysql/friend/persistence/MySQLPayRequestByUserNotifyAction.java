package it.unipv.ingsw.lasout.model.notify.action.mysql.friend.persistence;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.mysql.MySQLNotifyActionPersistence;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPayRequestByUserNotifyAction extends MySQLNotifyActionPersistence {

    public MySQLPayRequestByUserNotifyAction() {

    }

    @Override
    public void innerLoad(ResultSet resultSet, INotifyAction iNotifyAction) throws SQLException {

    }

    @Override
    public void innerSave(DBQuery.Builder query, INotifyAction iNotifyAction) throws SQLException {

    }


}
