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
     * @return l'istanza singleton del UserDAO
     */
    public static UserDAO getInstance() {
        if (istance == null){
            istance= new UserDAO();
        }
        return istance;
    }
    /**
     * Rendo il costruttore privato
     */
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

        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelectAllInformationsOfAUser = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_A_USER, user.getId());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelectAllInformationsOfAUser);

        //prendo il risultato della query
        ResultSet resultOfQuerySelectAllInformationsOfAUser = querySelectAllInformationsOfAUser.getResultSet();
        //controllo il risultato della query (faccio ".next()" perché senò punterei a una cella inesistente)
        if(resultOfQuerySelectAllInformationsOfAUser == null || !resultOfQuerySelectAllInformationsOfAUser.next()) throw new UserNotFoundException("user not found");

        //creo l'oggetto da ritornare
        User rawUserWithAllInformation = new User();
        //imposto i valori letti da database
        rawUserWithAllInformation.setId      (resultOfQuerySelectAllInformationsOfAUser.getInt   ("id"));
        rawUserWithAllInformation.setUsername(resultOfQuerySelectAllInformationsOfAUser.getString("username"));
        rawUserWithAllInformation.setPassword(resultOfQuerySelectAllInformationsOfAUser.getString("password"));
        rawUserWithAllInformation.setNotifies(getNotifies(user));

        System.out.println(rawUserWithAllInformation);

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
        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelectAllInformationsOfEveryUser = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_EVERY_USER);

        //esecuzione della query
        DatabaseUtil.getInstance().executeQuery(querySelectAllInformationsOfEveryUser);

        //"resultOfQuerySelectAllInformationsOfEveryUser" prende il risultato della query appena fatta
        ResultSet resultOfQuerySelectAllInformationsOfEveryUser = querySelectAllInformationsOfEveryUser.getResultSet();
        //se la query non da risultati o non c'è niente dopo (perché il primo carattere non è nulla) allora viene lanciata l'eccezione
        if(resultOfQuerySelectAllInformationsOfEveryUser == null || !resultOfQuerySelectAllInformationsOfEveryUser.next()) throw new UserNotFoundException("User not found");

        //creazione di una lista bean in cui metto le informazioni prese dalla query
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
        DBQuery queryUpdatePassword = DatabaseUtil.getInstance().createQuery(QUERY_UPDATE_THE_PASSWORD_OF_AN_EXISTING_USER, user.getUsername());
        DatabaseUtil.getInstance().executeQuery(queryUpdatePassword);
        ResultSet resultSet = queryUpdatePassword.getResultSet();

        //if(resultSet!=null) throw new UserNotFoundException("User not found");

        queryUpdatePassword.close();
    }

    //eliminazione di un utente presente nel dB
    @Override
    public void delete(User user) throws Exception {
        DBQuery queryDeleteUser = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_AN_EXISTING_USER, user.getUsername());
        DatabaseUtil.getInstance().executeQuery(queryDeleteUser);
        ResultSet resultSetDeleteUser = queryDeleteUser.getResultSet();

        //if(resultSet!=null) throw new UserNotFoundException("User not found");

        queryDeleteUser.close();
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
        User userTest1 = new User();
        userTest1.setUsername("Giovanni Giorgio Vincenzini");
        userTest1.setPassword("39");


        User userTest2 = new User();
        userTest2.setUsername("Paperon dei Paperoni");
        userTest2.setPassword("39XlMp780!39XlMp780!39XlMp780!39XlMp780!39XlMp780");

        System.out.println(userTest1);
        System.out.println(userTest2);

        UserDAO.getInstance().save(userTest1);
        UserDAO.getInstance().save(userTest2);

        //mi aspetto di non vedere più nel DB lo userTest1
        UserDAO.getInstance().delete(userTest1);

        //TODO risolvere il problema degli ID che non vedo incrementati
        //UserDAO.getInstance().getRaw(userTest1);

    }

}
