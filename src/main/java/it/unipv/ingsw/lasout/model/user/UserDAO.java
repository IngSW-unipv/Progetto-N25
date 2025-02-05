package it.unipv.ingsw.lasout.model.user;


import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO implements IDao<User> {

    private static final UserDAO INSTANCE = new UserDAO();
    public static UserDAO getInstance() {
        return INSTANCE;
    }

    private static final String QUERY_GET_1 =
            "SELECT * " +
                    "FROM `user`" +
                    "WHERE id = ?;";

    private static final String QUERY_GROUPSOF_1 =
            "SELECT group_id " +
                    "FROM `usergroup`" +
                    "WHERE user_id = ?;";


    public User getRaw(User user) throws SQLException {

        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GET_1, user.getId());
        DatabaseUtil.getInstance().executeQuery(query);


        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException("user not found");


        User savedUser = new User();
        savedUser.setId(resultSet.getInt("id"));
        savedUser.setUsername(resultSet.getString("username"));
        savedUser.setPassword(resultSet.getString("password"));

        return user;
    }

    @Override
    public User get(User user) throws Exception {
        User savedUser = getRaw(user);

        List<Group> groups = groupsOfUser(savedUser);
        savedUser.setGroups(groups);

        return savedUser;
    }

    public List<Notify> getNotifies(User user) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GET_1, user.getId());
        return null;
    }

    public List<Group> groupsOfUser(User user) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GROUPSOF_1, user.getId());

        List<Group> groups = new ArrayList<>();

        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet resultSet = query.getResultSet();
        if(resultSet == null) return groups;

        while(resultSet.next()) {
            Group group = new Group();
            group.setId(resultSet.getInt("id"));
            GroupDao.getInstance().getRaw(group);
        }

        query.close();


        return groups;

    }


    @Override
    public List<User> getAll() throws Exception {
        //creazione della querySelectAll di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelectAll = DatabaseUtil.getInstance().createQuery("SELECT *" +
                                                                           "FROM user" );
        //esecuzione della querySelectAll
        DatabaseUtil.getInstance().executeQuery(querySelectAll);

        //"resultSet" prende il risultato della querySelectAll appena fatta
        ResultSet resultSet = querySelectAll.getResultSet();
        //se la querySelectAll non da risultati o non c'è niente dopo c'è dopo il primo carattere allora viene lanciata l'eccezione
        if(resultSet == null || !resultSet.next()) throw new UserNotFoundException("User not found");

        //creazione di un bean in cui metto l'id preso dalla querySelectAll
        User savedUser = new User();
        savedUser.setId(resultSet.getInt("id"));

        return List.of();
    }

    @Override
    public void save(User user) throws Exception {

        String username, password;
        //dema sono cla che stai a fare con uno scanner system in??? qui stiamo idealizando niente fa nulla e sopratutto non lo facciamo dalla console
        //interazione con l'utente per l'inserimento
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me the username:");
        username= scanner.nextLine();
        System.out.println("Give me the password");
        password = scanner.nextLine();

        //query di inserimento di un nuovo user
        DBQuery queryInsert = DatabaseUtil.getInstance().createQuery("INSERT INTO user(?, ?)", username, password);
        //esecuzione della queryInsert
        DatabaseUtil.getInstance().executeQuery(queryInsert);

        //TODO controlli vari per la corretta esecuzione della query
        queryInsert.close();
    }

    //modifica delle informazioni di un utente presente nel dB
    @Override
    public void update(User user, String[] params) throws Exception {

    }

    //eliminazione di un utente presente nel dB
    @Override
    public void delete(User user) throws Exception {

    }
}
