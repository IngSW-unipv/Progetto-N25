package it.unipv.ingsw.lasout.model.user;


import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.NotifyDAO;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IDao<User> {

    /**
     * Istanza singola del GroupDao (implementazione singleton)
     */
    private static UserDAO istance = null;

    /**
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

    /**
     * Elenco delle query da far eseguire ai vari metodi della classe UserDAO
     */

    private static final String QUERY_SELECT_ALL_INFORMATIONS_OF_A_USER = "SELECT * " +
                                                                          "FROM $user$" +
                                                                          "WHERE id = ?;";
    private static final String QUERY_SELECT_ALL_GROUPS_OF_A_USER = "SELECT group_id " +
                                                                    "FROM $usergroup$" +
                                                                    "WHERE user_id = ?;";
    private static final String QUERY_SELECT_ALL_INFORMATIONS_OF_EVERY_USER = "SELECT *" +
                                                                             "FROM $user$;";
    private static final String QUERY_INSERT_NEW_USER_WITH_ID = "INSERT INTO $user$ (id, username, password, email) VALUES (?, ?, ?, ?);";
    private static final String QUERY_INSERT_NEW_USER_WITHOUT_ID = "INSERT INTO $user$ (username, password, email) VALUES (?, ?, ?);";
    private static final String QUERY_DELETE_AN_EXISTING_USER = "DELETE FROM $user$ WHERE id = ?;";
    private static final String QUERY_SELECT_ID_FROM_HIS_CREDENTIALS = "SELECT id FROM $user$ WHERE username = ? AND password = ? AND email = ?;";

    private static final String QUERY_SELECT_ALL_NOTIFIES_OF_USER  = "" +
            "SELECT id " +
            "FROM \\'notify\\'" +
            "WHERE user_id =  ?;";






    /**
     * Metodo che prende solo i dati elementari dell'utente che mi interessa passando dal suo id
     * @param user oggetto contenente il solo identificatore dell'entità
     * @return
     * @throws Exception
     */
    @Override
    public User getRaw(User user) throws Exception {

        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_A_USER, user.getId());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query (faccio ".next()" perché senò punterei a una cella inesistente)
        if(rS == null || !rS.next()) throw new UserNotFoundException("With this id:"+user.getId());

        //creo l'oggetto da ritornare
        User rawUserWithAllPrimaryInformation = new User();
        //imposto i valori letti da database
        rawUserWithAllPrimaryInformation.setId(rS.getInt("id"));
        rawUserWithAllPrimaryInformation.setUsername(rS.getString("username"));
        rawUserWithAllPrimaryInformation.setPassword(rS.getString("password"));


        //System.out.println(rawUserWithAllPrimaryInformation);

        return rawUserWithAllPrimaryInformation;
    }


    /**
     * Metodo che mi dà tutti i gruppi che hanno come partecipante quello che gli dico io (tramite il suo id)
     * @param user oggetto contenente il solo identificatore dell'entità
     * @return savedUser
     * @throws Exception
     */
    @Override
    public User get(User user) throws Exception {
        User savedUser = getRaw(user);

        List<Group> groups = groupsOfUser(user);
        savedUser.setGroups(groups);

        savedUser.setNotifies(getNotifications(user));
        return savedUser;
    }


    /**
     * Metodo che restituisce tutti gli utenti presenti nel DB
     * @return Un arraylist di tutti gli user presenti nella tabella user nel database
     * @throws Exception Errore nel esequzione della query SQL
     */
    @Override
    public ArrayList<User> getAll() throws Exception {
        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_EVERY_USER);

        //esecuzione della query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //"rS" prende il risultato della query appena fatta
        ResultSet rS = querySelect.getResultSet();
        //se la query non da risultati o non c'è niente dopo (perché il primo carattere non è nulla) allora viene lanciata l'eccezione
        if(rS == null) throw new SQLException("querySelect error");

        //creazione di una lista bean in cui metto le informazioni prese dalla query
        ArrayList<User> usersList = new ArrayList<>();

        //ciclo while per prendere tutti i dati dell'utente che mi ha restituito la query
        while(rS.next()) {
            User rawUserWithAllInformation = new User();

            rawUserWithAllInformation.setId(rS.getInt("id"));
            rawUserWithAllInformation.setUsername(rS.getString("username"));
            rawUserWithAllInformation.setPassword(rS.getString("password"));
            rawUserWithAllInformation.setEmail(rS.getString("email"));

            usersList.add(rawUserWithAllInformation);
        }

        return usersList;
    }


    /**
     * Metodo che aggiunge un nuovo utente al DB
     * @param user dean di un utente che si vuole aggiungere(registrare) nel database se non è presente
     * @throws Exception errore nella esecuzione della query
     */
    @Override
    public void save(User user) throws Exception {
        DBQuery queryInsert;

        if(user.getId()!=0){
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITH_ID, user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
        }else{
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITHOUT_ID, user.getUsername(), user.getPassword(), user.getEmail());
        }

        DatabaseUtil.getInstance().executeQuery(queryInsert);
        ResultSet rS = queryInsert.getResultSet();

        if(rS!=null)throw new Exception();

        queryInsert.close();
    }
    /*
    public int saveUser(User user) throws Exception {
        DBQuery queryInsert;

        if(user.getId()!=0){
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITH_ID, user.getId(), user.getUsername(), user.getPassword());
        }else{
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITHOUT_ID, user.getUsername(), user.getPassword());
        }

        DatabaseUtil.getInstance().executeQuery(queryInsert);
        ResultSet rS = queryInsert.getResultSet();

        if(rS!=null)throw new Exception();

        queryInsert.close();
        return rS.getInt("id");
    }
    */
    @Override
    public void update(User user) throws Exception {
        delete(user);
        save(user);
    }


    /**
     * Eliminazione di un utente presente nel dB
     * @param user dean di un utente che si vuole eliminare nel database se presente
     * @throws Exception errore nella esecuzione della query o errore nella generazione della chiave
     */
    @Override
    public void delete(User user) throws Exception {
        DBQuery queryDeleteUser = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_AN_EXISTING_USER, user.getId());
        DatabaseUtil.getInstance().executeQuery(queryDeleteUser);
        ResultSet resultSetDeleteUser = queryDeleteUser.getResultSet();

        //controllare sto if
        if(resultSetDeleteUser!=null) throw new UserNotFoundException(""+user.getId());

        queryDeleteUser.close();
    }



    public List<User> getFriends(User  user){
        List<User> friends = new ArrayList<>();



        return friends;
    }

    public List<Notify> getNotifications(User  user) throws Exception {
        return NotifyDAO.getInstance().notifiesOf(user);
    }

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
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

    public User userSearchBasedOnTheirCredentials(User user) throws Exception {
        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ID_FROM_HIS_CREDENTIALS, user.getUsername(), user.getPassword(), user.getEmail());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query
        if(rS == null) throw new UserNotFoundException(" with this id:"+user.getId());

        User fictitiousUser = new User(rS.getInt("id"));

        querySelect.close();
        return fictitiousUser;
    }




}
