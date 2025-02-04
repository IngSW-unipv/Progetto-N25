package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.dao.DBQuery;
import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.util.DatabaseUtil;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.List;

public class UserDAO implements IDao<User> {


    @Override
    public User get(User user) throws Exception {


        DBQuery  query = DatabaseUtil.getInstance().createQuery("" +
                "SELECT *" +
                "FROM user" +
                "WHERE id = ?", user.getId() );
        DatabaseUtil.getInstance().executeQuery(query);


        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException("user not found");

        int id = resultSet.getInt("id");

        User savedUser = new User();
        savedUser.setId(id);


        query.setQuery(
                "SELECT *" +
                "FROM relgroupuser" +
                "WHERE user_id = ?");
        query.setParams(savedUser.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        resultSet = query.getResultSet();
        if(resultSet == null) throw new RuntimeException("user not found");

        while(resultSet.next()) {

            Group group = GroupDao.getInstance().get(new Group(resultSet.getInt("group_id")));
            savedUser.getGroups().add(group);

        }


        query.close();

        return savedUser;


    }

    @Override
    public List<User> getAll() throws Exception {
        return List.of();
    }

    @Override
    public void save(User user) throws Exception {

    }

    @Override
    public void update(User user, String[] params) throws Exception {

    }

    @Override
    public void delete(User user) throws Exception {

    }
}
