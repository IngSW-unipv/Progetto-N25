package it.unipv.ingsw.lasout.model.notify.action.group.mysql;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.mysql.MySQLNotifyActionPersistence;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPayRequestByGroupNotifyActionPersistence extends MySQLNotifyActionPersistence {

    public MySQLPayRequestByGroupNotifyActionPersistence() {
        this.tableName = "payrequestgroupnotify";
        this.update  = "to_user_id = ?, from_group_id = ?";
        this.insert  = "to_user_id, from_user_id";
    }

    @Override
    public void innerLoad(ResultSet resultSet, INotifyAction iNotifyAction) throws SQLException {

    }

    @Override
    public void innerSave(DBQuery.Builder query, INotifyAction iNotifyAction) throws SQLException {

    }
}
