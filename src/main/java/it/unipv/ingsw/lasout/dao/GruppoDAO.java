package it.unipv.ingsw.lasout.dao;

import it.unipv.ingsw.lasout.model.Group;
import it.unipv.ingsw.lasout.util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GruppoDAO {


    private Group group;

    public GruppoDAO(Group group) {
        this.group = group;
    }



    public Group load() throws SQLException {




        DBQuery query =  DatabaseUtil.getInstance().createQuery("SELECT * " +
                "FROM `gruppo` " +
                "WHERE id = ?;", group.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) throw new RuntimeException("Could not find group by  id = '" + group.getId() + "'");

        int id = resultSet.getInt("id");
        Group loadedGroup = new Group(id);

        query.close();
        return loadedGroup;

    }

    public void save() throws SQLException {
        DBQuery query =  DatabaseUtil.getInstance().createQuery("INSERT " +
                "INTO `gruppo` (id, admin_id) " +
                "VALUES (?, ?)", group.getId(), group.getAdmin()  != null ? group.getAdmin().getId() : null);

        DatabaseUtil.getInstance().executeQuery(query);
        query.close();
    }
}
