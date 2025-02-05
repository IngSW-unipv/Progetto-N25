package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DatabaseUtil;

import java.sql.ResultSet;
import java.util.List;

public class UserDAO implements IDao<User> {

    private static final UserDAO INSTANCE = new UserDAO();
    public static UserDAO getInstance() { return INSTANCE; }
    private UserDAO() {

    }

    private static final String FIRST_QUERY = "SELECT * FROM `user` WHERE id = ?;";

    @Override
    public User get(User user) throws Exception {


        DBQuery  query = DatabaseUtil.getInstance().createQuery(FIRST_QUERY, user.getId() );
        DatabaseUtil.getInstance().executeQuery(query);


        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException("user not found");

        int id = resultSet.getInt("id");
        String username =  resultSet.getString("username");

        User savedUser = new User();
        savedUser.setId(id);
        savedUser.setUsername(username);


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
