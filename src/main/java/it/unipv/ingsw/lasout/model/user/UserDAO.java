package it.unipv.ingsw.lasout.model.user;


import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.cashbook.RdbCashbookDao;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.notify.INotifyDAO;
import it.unipv.ingsw.lasout.model.notify.MySQLNotifyDAO;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.exception.UserAlreadyExistException;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {


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

    private INotifyDAO iNotifyDAO;

    public UserDAO(){

        iNotifyDAO = DaoFactory.getNotifyDAO();

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
    private static final String QUERY_SELECT_ID_FROM_HIS_USERNAME_PASSWORD = "SELECT id FROM $user$ WHERE username = ? AND password = ?;";
    private static final String QUERY_SELECT_ID_FROM_HIS_EMAIL_PASSWORD = "SELECT id FROM $user$ WHERE email = ? AND password = ?;";
    private static final String QUERY_SELECT_ID_FROM_HIS_CREDENTIALS_FOR_CREATING_ACCOUNT = "SELECT id FROM $user$ WHERE username = ? AND email = ? AND password = ?;";
    private static final String QUERY_UPDATE_PASSWORD = "UPDATE $user$ SET password = ? WHERE id = ?;";






    /**
     * Metodo che prende solo i dati elementari dell'utente che mi interessa passando dal suo id
     * @param user oggetto utente di cui mi interessa solamente il suo id
     * @return un utente con i suoi soli dati elementari ()
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente del quale si vogliono i dati
     */
    @Override
    public User getRaw(User user) throws SQLException, UserNotFoundException{

        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_INFORMATIONS_OF_A_USER, user.getId());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query (faccio ".next()" perché senò punterei a una cella inesistente)
        if(rS == null || !rS.next()) throw new UserNotFoundException(" with this id:"+user.getId());

        //creo l'oggetto da ritornare
        User rawUserWithAllPrimaryInformation = new User();

        //imposto i valori letti da database
        rawUserWithAllPrimaryInformation.setId(rS.getInt("id"));
        rawUserWithAllPrimaryInformation.setUsername(rS.getString("username"));
        rawUserWithAllPrimaryInformation.setPassword(rS.getString("password"));
        rawUserWithAllPrimaryInformation.setEmail(rS.getString("email"));

        return rawUserWithAllPrimaryInformation;
    }


    /**
     * Metodo che mi dà tutte le informazioni di un dato utente (tramite il suo id)
     * @param user oggetto contenente il solo identificatore dell'entità
     * @return l'utente con tutte le sue informazioni
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente del quale si vogliono i dati
     */
    @Override
    public User get(User user) throws SQLException, UserNotFoundException {
        //per semplicità uso la getRaw per la ricerca delle sue informazioni base\primitive (id, username, email, password)
        User savedUser = getRaw(user);

        List<Group> groups = groupsOfUser(user);
        savedUser.setGroups(groups);

        //associo i cashbook di un utente
        List<Cashbook> cashbooks = getCashbooks(user);
        savedUser.setCashbooks(cashbooks);

        //savedUser.setNotifies(getNotifications(user));
        return savedUser;
    }


    /**
     * Metodo che restituisce tutti gli utenti presenti nel DB con i loro dati primitivi
     * @return Una lista di tutti gli user presenti nella table user nel DB
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     */
    @Override
    public ArrayList<User> getAll() throws SQLException {

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
     * Metodo che aggiunge un nuovo utente al Database
     * @param user oggetto da salvare, con tutte le sue informazioni
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserAlreadyExistException eccezione nel caso in cui la query abbia già trovato l'utente con uguali credenziali
     */
    @Override
    public void save(User user) throws SQLException {
        DBQuery queryInsert;

        if(user.getId()!=0){
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITH_ID, user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
        }else{
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITHOUT_ID, user.getUsername(), user.getPassword(), user.getEmail());
        }

        DatabaseUtil.getInstance().executeQuery(queryInsert);

        queryInsert.close();
    }


    @Override
    public void update(User userCarrier) throws Exception {
        delete(userCarrier);
        save(userCarrier);
    }

    @Override
    public void updatePassword(User user, String newPassword) throws SQLException, UserNotFoundException {

        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery queryUpdate = DatabaseUtil.getInstance().createQuery(QUERY_UPDATE_PASSWORD, newPassword, user.getId());

        //esecuzione della query
        DatabaseUtil.getInstance().executeQuery(queryUpdate);

        //"rS" prende il risultato della query appena fatta
        ResultSet rS = queryUpdate.getResultSet();
        //se la query non da risultati o non c'è niente dopo (perché il primo carattere non è nulla) allora viene lanciata l'eccezione
        if(rS != null) throw new SQLException("querySelect error");

        queryUpdate.close();
    }


    /**
     * Eliminazione dell'account di un utente presente nel dB
     * @param user utente del quale voglio eliminare l'account
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente che si vuole eliminare
     */
    @Override
    public void delete(User user) throws SQLException, UserNotFoundException {
        DBQuery queryDeleteUser = DatabaseUtil.getInstance().createQuery(QUERY_DELETE_AN_EXISTING_USER, user.getId());
        DatabaseUtil.getInstance().executeQuery(queryDeleteUser);
        ResultSet resultSetDeleteUser = queryDeleteUser.getResultSet();

        //se il resultSet è nullo vuol dire che non ha trovato nessun utente da eliminare con quel'id
        if(resultSetDeleteUser!=null) throw new UserNotFoundException(user.getUsername());

        queryDeleteUser.close();
    }








    @Override
    public List<User> getFriends(User user){

        List<User> friends = new ArrayList<>();

        return friends;
    }

    @Override
    public List<Cashbook> getCashbooks(User user){

        List<Cashbook> cashbooks = null;
        try {
            cashbooks = RdbCashbookDao.getInstance().getAllUserCashbooks(user);
        } catch (Exception noCashbookFound) {
            throw new RuntimeException(noCashbookFound);
        }

        return cashbooks;
    }

    @Override
    public List<Notify> getNotifications(User user) throws Exception {

        return iNotifyDAO.notifiesOf(user);
    }


    @Override
    public List<Group> groupsOfUser(User user) throws SQLException, UserNotFoundException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ALL_GROUPS_OF_A_USER, user.getId());

        List<Group> groups = new ArrayList<>();

        DatabaseUtil.getInstance().executeQuery(query);

        ResultSet rS = query.getResultSet();
        if(rS == null) return groups;

        while(rS.next()) {
            Group group = new Group();
            group.setId(rS.getInt("group_id"));
            Group groupOf  = GroupDao.getInstance().getRaw(group);
            groups.add(groupOf);
        }

        query.close();
        return groups;

    }


    @Override
    public User userSearchIdBasedOnTheirUsernameAndPassword(User userCarrier) throws SQLException, UserNotFoundException {
        //creazione della query di ricerca nel DB di tipo "DBQuery" in base al suo username e password
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ID_FROM_HIS_USERNAME_PASSWORD, userCarrier.getUsername(), userCarrier.getPassword());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query
        if(rS == null || !rS.next()) throw new UserNotFoundException("");

        //salvo tutte le info dell'utente passatomi
        User user = new User();
        userCarrier.setId(rS.getInt("id"));
        user = get(userCarrier);

        /*
        se volesi restituire solamente id e le credenziali
        user.setId(rS.getInt("id"));
        user.setUsername(userCarrier.getUsername());
        user.setPassword(userCarrier.getPassword());
        */

        querySelect.close();
        return user;
    }


    @Override
    public User userSearchIdBasedOnTheirEmailAndPassword(User userCarrier) throws SQLException, UserNotFoundException {
        //creazione della query di ricerca nel DB di tipo "DBQuery" in base al suo username e password
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ID_FROM_HIS_EMAIL_PASSWORD, userCarrier.getEmail(), userCarrier.getPassword());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query
        if(rS == null || !rS.next()) throw new UserNotFoundException("");

        //salvo tutte le info dell'utente passatomi
        User user = new User();
        userCarrier.setId(rS.getInt("id"));
        user = get(userCarrier);

        /*
        userIdEmailPassword.setId(rS.getInt("id"));
        userIdEmailPassword.setEmail(user.getEmail());
        userIdEmailPassword.setPassword(user.getPassword());
         */

        querySelect.close();
        return user;
    }


    @Override
    public User userNotFoundForCreateAccount(User user) throws SQLException, UserNotFoundException {

        //creazione della query di ricerca nel DB di tipo "DBQuery" in base al suo username e password
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ID_FROM_HIS_CREDENTIALS_FOR_CREATING_ACCOUNT, user.getUsername(), user.getEmail(), user.getPassword());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query
        if(rS == null || !rS.next()) throw new UserNotFoundException("");

        //se invece è stato trovato salvo l'id dell'utente appena trovato in un utente fittizio
        User idUser = new User(rS.getInt("id"));

        querySelect.close();
        return idUser;
    }
}
