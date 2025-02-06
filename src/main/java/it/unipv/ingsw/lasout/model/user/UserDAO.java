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

    /**
     * Istanza singola del GroupDao (implementazione singleton)
     */
    private static UserDAO istance = null;

    /**
     *
     * @return
     */
    public static UserDAO getInstance() {
        if (istance == null){
            istance= new UserDAO();
        }
        return istance;
    }
    //rendo il costruttore privato
    private UserDAO(){

    }

    ////////////////////////////////////elenco delle query da far eseguire ai vari metodi della classe UserDAO////////////////////////////////////
    private static final String QUERY_SELECT_ALL_INFORMATIONS_OF_A_USER =      "SELECT * " +
                                                                               "FROM $user$" +
                                                                               "WHERE id = ?;";

    private static final String QUERY_SELECT_ALL_GROUPS_OF_A_USER =            "SELECT group_id " +
                                                                               "FROM $usergroup$" +
                                                                               "WHERE user_id = ?;";

    private static final String QUERY_SELECT_ALL_INFORMATIONS_OF_EVERY_USER =  "SELECT *" +
                                                                               "FROM $user$;";

    private static final String QUERY_INSERT_NEW_USER =                        "INSERT INTO $user$ (username, password) VALUES (?, ?);";

    private static final String QUERY_UPDATE_THE_PASSWORD_OF_AN_EXISTING_USER= "UPDATE user" +
                                                                               "SET $password$ = ?" +
                                                                               "WHERE username = ?;";

    private static final String QUERY_DELETE_AN_EXISTING_USER =                "DELETE FROM $user$ WHERE username = ?;";





    //prendo solo i dati elementari dell'utente che mi interessa (dal suo id a tutti gli altri dati)
    public User getRaw(User user) throws Exception {

        //creo la query
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_A_USER, user.getId());
        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(query);

        //prendo il risultato della query
        ResultSet resultOfQuerySelect = query.getResultSet();
        //controllo il risultato della query (faccio ".next()" pk senò punterei a una cella inesistente)
        if(resultOfQuerySelect == null || !resultOfQuerySelect.next()) throw new UserNotFoundException("user not found");

        //creo l'oggetto da ritornare
        User rawUserWithAllInformation = new User();
        //imposto i valori letti da database
        rawUserWithAllInformation.setId      (resultOfQuerySelect.getInt   ("id"));
        rawUserWithAllInformation.setUsername(resultOfQuerySelect.getString("username"));
        rawUserWithAllInformation.setPassword(resultOfQuerySelect.getString("password"));
        rawUserWithAllInformation.setNotifies(getNotifies(user));

        return rawUserWithAllInformation;
    }


    public List<Notify> getNotifies(User user) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_A_USER, user.getId());
        return null;
    }


    //mi dà tutti i gruppi che hanno come partecipante quello che gli dico io (tramite l'id)
    @Override
    public User get(User user) throws Exception {
        User savedUser = getRaw(user);

        List<Group> groups = groupsOfUser(user);
        savedUser.setGroups(groups);

        return savedUser;
    }


    public List<Group> groupsOfUser(User user) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_GROUPS_OF_A_USER, user.getId());

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
    public ArrayList<User> getAll() throws Exception {
        //creazione della "querySelectAllInformationsOfEveryUser" di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelectAllInformationsOfEveryUser = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_EVERY_USER);
        //esecuzione della "querySelectAllInformationsOfEveryUser"
        DatabaseUtil.getInstance().executeQuery(querySelectAllInformationsOfEveryUser);

        //"resultOfQuerySelectAllInformationsOfEveryUser" prende il risultato della "querySelectAllInformationsOfEveryUser" appena fatta
        ResultSet resultOfQuerySelectAllInformationsOfEveryUser = querySelectAllInformationsOfEveryUser.getResultSet();
        //se la query non da risultati o non c'è niente dopo (pk il primo carattere non è nulla) allora viene lanciata l'eccezione
        if(resultOfQuerySelectAllInformationsOfEveryUser == null || !resultOfQuerySelectAllInformationsOfEveryUser.next()) throw new UserNotFoundException("User not found");

        //creazione di una lista bean in cui metto le informazioni prese dalla "querySelectAllInformationsOfEveryUser"
        ArrayList<User> usersList = new ArrayList<>();

        //ciclo while per prendere tutti i dati dell'utente che mi ha returnato la query
        while(resultOfQuerySelectAllInformationsOfEveryUser.next()) {
            User user = new User();
            user.setId      (resultOfQuerySelectAllInformationsOfEveryUser.getInt   ("id"));
            user.setUsername(resultOfQuerySelectAllInformationsOfEveryUser.getString("username"));
            user.setPassword(resultOfQuerySelectAllInformationsOfEveryUser.getString("password"));
            usersList.add(user);
        }

        return usersList;
    }

    //metodo che aggiunge un nuovo user tramite query di insert
    @Override
    public void save(User user) throws Exception {
        DBQuery queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER, user.getUsername(), user.getPassword());
        DatabaseUtil.getInstance().executeQuery(queryInsert);

        //TODO controlli vari per la corretta esecuzione della query
        queryInsert.close();
    }

    //modifica delle informazioni di un utente presente nel dB
    @Override
    public void update(User user, String[] params) throws Exception {
        DBQuery queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_UPDATE_THE_PASSWORD_OF_AN_EXISTING_USER, user.getUsername());
        DatabaseUtil.getInstance().executeQuery(queryInsert);
        ResultSet resultSet = queryInsert.getResultSet();

        //if(resultSet!=null) throw new UserNotFoundException("User not found");

        queryInsert.close();
    }

    //eliminazione di un utente presente nel dB
    @Override
    public void delete(User user) throws Exception {
        DBQuery queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_AN_EXISTING_USER, user.getUsername());
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
