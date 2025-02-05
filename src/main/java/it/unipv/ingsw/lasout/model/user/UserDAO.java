package it.unipv.ingsw.lasout.model.user;


import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO implements IDao<User> {

    //istanza singleton dell' UserDao
    private static final UserDAO INSTANCE = new UserDAO();
    public static UserDAO getInstance() {
        return INSTANCE;
    }
    //rendo il costruttore privato
    private UserDAO(){

    }

    //elenco delle query da far eseguire ai vari metodi del DAO
    private static final String QUERY_GET_1 = "SELECT * " +
                                              "FROM £user£" +
                                              "WHERE id = ?;";

    private static final String QUERY_GROUPSOF_1 = "SELECT group_id " +
                                                   "FROM £usergroup£" +
                                                   "WHERE user_id = ?;";

    private static final String QUERY_INSERT_NEW_USER_1 = "INSERT INTO £user£ (username, password) VALUES (?, ?);";

    private static final String QUERY_DELETE_EXIST_USER_1 = "DELETE FROM £utenti£ WHERE username = ?;";





    //prendo solo i dati elementari dell'utente che mi interessa (dal suo id a tutti gli altri dati)
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
        List<User> users = new ArrayList<>();
        //User savedUser = new User();
        //ciclo while per prendere tutti i dati dell'utente
        while(resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);
        }

        return users;
    }

    //metodo che aggiunge un nuovo user tramite query di insert
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
        ResultSet resultSet = queryInsert.getResultSet();

        //if(resultSet!=null) throw new UserNotFoundException("User not found");

        queryInsert.close();
    }


    ////////////////////////QUESTA COSA è STANDARD QUINDI COPIA E INCOLLA SE SERVE////////////////////
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    //main di test
    public static void main(String[] args) throws Exception {
        //controllo iniziale per il DB
        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Can't initialize the DB: \n" + e);
            System.exit(1);
            return;
        }
        //essendo uh singleton devo ogni volta crearlo
        User userTest = new User();

        //System.out.println(userTest);
        userTest.setUsername("Giovanni Giorgio");
        userTest.setPassword("1");
        UserDAO.getInstance().save(userTest);

        userTest.setUsername("Giovanni Giorgio");
        userTest.setPassword("1");
        UserDAO.getInstance().save(userTest);

    }

}
