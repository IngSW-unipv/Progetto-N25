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

    //singleton
    private static final UserDAO INSTANCE = new UserDAO();
    public static UserDAO getInstance() {
        return INSTANCE;
    }
    private UserDAO(){

    }

    private static final String QUERY_GET_1 =
            "SELECT * " +
                    "FROM `user`" +
                    "WHERE id = ?;";

    private static final String QUERY_GROUPSOF_1 =
            "SELECT group_id " +
                    "FROM `usergroup`" +
                    "WHERE user_id = ?;";

    private static final String QUERY_INSERT_NEW_USER_1 = "INSERT INTO £utenti£ (username, password) VALUES (?, ?);";

    private static final String QUERY_DELETE_EXIST_USER_1 = "DELETE FROM £utenti£ WHERE username = ?;";





    //prendo solo i dati elementari dell'utente che mi interessa (il suo id)
    public User getRaw(User user) throws Exception {

        //creo la query
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GET_1, user.getId());
        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(query);

        //prendo il risultato della query
        ResultSet resultSet = query.getResultSet();
        //controllo il risultato della query (faccio ".next()" pk senò punterei a una cella inesistente)
        if(resultSet == null || !resultSet.next()) throw new RuntimeException("user not found");

        //creo l'oggetto da ritornare
        User savedUser = new User();
        //imposto i valori letti da database
        savedUser.setId(resultSet.getInt("id"));
        savedUser.setUsername(resultSet.getString("username"));
        savedUser.setPassword(resultSet.getString("password"));
        savedUser.setNotifies(getNotifies(user));

        return savedUser;
    }

    //mi dà tutti i gruppi che hanno come partecipante quello che gli dico io (tramite l'id)
    @Override
    public User get(User user) throws Exception {
        User savedUser = getRaw(user);

        //"groupsOfUser(savedUser)"
        List<Group> groups = groupsOfUser(user);
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
            group.setId(resultSet.getInt("group_id"));
            Group groupOf  = GroupDao.getInstance().getRaw(group);
            groups.add(groupOf);
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

    //metodo che aggiunge un nuovo user
    @Override
    public void save(User user) throws Exception {
        DBQuery queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_1, user.getUsername(), user.getPassword());
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
        DBQuery queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_EXIST_USER_1, user.getUsername());
        DatabaseUtil.getInstance().executeQuery(queryInsert);

        queryInsert.close();
    }
}
