package it.unipv.ingsw.lasout.model.grup;

import it.unipv.ingsw.lasout.dao.DBQuery;
import it.unipv.ingsw.lasout.util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GroupDao implements IGroupDao{


    private static final GroupDao INSTANCE = new GroupDao();
    public static GroupDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Group get(Group group) throws SQLException {

        DBQuery query =  DatabaseUtil.getInstance().createQuery("SELECT * " +
                "FROM `gruppo` " +
                "WHERE id = ?;", group.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet resultSet = query.getResultSet();
        if(!resultSet.next()) throw new RuntimeException("Could not find group by  id = '" + group.getId() + "'");

        int id = resultSet.getInt("id");
        Group loadedGroup = new Group(group.getId());

        query.close();
        return loadedGroup;

    }

    @Override
    public List<Group> getAll() {
        return List.of();
    }

    @Override
    public void save(Group group) throws SQLException {

        DBQuery query =  DatabaseUtil.getInstance().createQuery("INSERT " +
                "INTO `gruppo` (id, admin_id) " +
                "VALUES (?, ?)", group.getId(), group.getAdmin()  != null ? group.getAdmin().getId() : null);
        DatabaseUtil.getInstance().executeQuery(query);
        query.close();

    }

    @Override
    public void update(Group group, String[] params) {

    }

    @Override
    public void delete(Group group) {

    }

}
