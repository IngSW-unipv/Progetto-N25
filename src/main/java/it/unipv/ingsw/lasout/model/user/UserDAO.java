package it.unipv.ingsw.lasout.model.user;


import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.group.IGroupDao;
import it.unipv.ingsw.lasout.model.notify.INotifyDAO;
import it.unipv.ingsw.lasout.model.notify.MySQLNotifyDAO;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.exception.UserAlreadyExistException;
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
    private static final String QUERY_SELECT_ID_FROM_HIS_USERNAME_PASSWORD = "SELECT id FROM $user$ WHERE username = ? AND password = ?;";
    private static final String QUERY_SELECT_ID_FROM_HIS_EMAIL_PASSWORD = "SELECT id FROM $user$ WHERE email = ? AND password = ?;";
    private static final String QUERY_SELECT_ID_FROM_HIS_CREDENTIALS_FOR_CREATING_ACCOUNT = "SELECT id FROM $user$ WHERE username = ? AND email = ? AND password = ?;";
    private static final String QUERY_UPDATE_PASSWORD = "UPDATE $user$ SET password = ? WHERE id = ?;";

    private static final String QUERY_SELECT_ALL_NOTIFIES_OF_USER  = "" +
            "SELECT id " +
            "FROM \\'notify\\'" +
            "WHERE user_id =  ?;";






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
    public User get(User user) throws SQLException, UserNotFoundException, Exception {
        User savedUser = getRaw(user);

        List<Group> groups = groupsOfUser(user);
        savedUser.setGroups(groups);

        savedUser.setNotifies(getNotifications(user));
        return savedUser;
    }


    /**
     * Metodo che restituisce tutti gli utenti presenti nel DB
     * @return Un arraylist di tutti gli user presenti nella tabella user nel database
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
     * Metodo che aggiunge un nuovo utente al Database tenendo conto dell'opzione "update" che avrà id=0
     * @param user oggetto da salvare, con tutte le sue informazioni
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserAlreadyExistException eccezione nel caso in cui la query abbia già trovato l'utente con uguali credenziali
     */
    @Override
    public void save(User user) throws SQLException, UserAlreadyExistException {
        DBQuery queryInsert;

        if(user.getId()!=0){
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITH_ID, user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
        }else{
            queryInsert = DatabaseUtil.getInstance().createQuery(QUERY_INSERT_NEW_USER_WITHOUT_ID, user.getUsername(), user.getPassword(), user.getEmail());
        }

        DatabaseUtil.getInstance().executeQuery(queryInsert);
        ResultSet rS = queryInsert.getResultSet();

        if(rS!=null)throw new UserAlreadyExistException(user.getUsername());

        queryInsert.close();
    }


    /**
     * Metodo di update fatto unendo i 2 metodi di delete e save dello user per semplicità
     * @param user utente del quale voglio modificare la password
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query del metodo "delete" non trovi l'account dell'utente che si vuole eliminare
     * @throws UserAlreadyExistException eccezione nel caso in cui la query del metodo "save" abbia già trovato l'utente con uguali credenziali
     */
    @Override
    public void update(User user) throws SQLException, UserNotFoundException, UserAlreadyExistException {

        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery queryUpdate = DatabaseUtil.getInstance().createQuery(QUERY_UPDATE_PASSWORD, user.getPassword(), user.getId());

        //esecuzione della query
        DatabaseUtil.getInstance().executeQuery(queryUpdate);

        //"rS" prende il risultato della query appena fatta
        ResultSet rS = queryUpdate.getResultSet();
        //se la query non da risultati o non c'è niente dopo (perché il primo carattere non è nulla) allora viene lanciata l'eccezione
        if(rS != null) throw new SQLException("querySelect error");

        queryUpdate.close();
    }


    /**
     * Eliminazione di un utente presente nel dB
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


    /**
     * Metodo che serve ad associare una lista di amici (altri utenti) a un certo utente
     * @param user utente che avrà una determinata lista di amici
     * @return la lista di amici
     */
    public List<User> getFriends(User user){

        List<User> friends = new ArrayList<>();

        return friends;
    }


    /**
     * Metodo che serve ad associare la lista di tutte le notifiche a un determinato utente
     * @param user utente che avrà una determinata lista di notifiche
     * @return la lista delle notifiche
     * @throws Exception eccezione lanciata dalla classe NotifyDAO
     */
    public List<Notify> getNotifications(User user) throws Exception {

        return MySQLNotifyDAO.getInstance().notifiesOf(user);

    }


    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
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

    /**
     * Metodo che fa la query di ricerca sul DB per cercare l'id di un utente che mi viene passato
     * Per discriminare se mandare l'utente in un metodo o nell'altro uso la variabile booleana nel bean che se è true
     * vuol dire che lo username è nullo e la email no, allora cercherò l'utente tramite email-password
     * @param user utente che mi viene passato per controllare qual è il suo id
     * @return fictitiousUser con solamente l'id dell utente con quelle date credenziali
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente con le credenziali che ha dato
     */
    public User userSearchIdBasedOnTheirCredentials(User user) throws SQLException, UserNotFoundException {
        User fictitiousUser = null;

        //lo cerco con la combo username-password
        if(user.getUsername() != null && user.getPassword() != null && user.getEmail() == null) fictitiousUser = userSearchIdBasedOnTheirUsernameAndPassword(user);

        //lo cerco con la combo email-password
        if(user.getUsername() == null && user.getPassword() != null && user.getEmail() != null) fictitiousUser = userSearchIdBasedOnTheirEmailAndPassword(user);

        //lo cerco con la combo username-email-password
        if(user.getUsername() != null && user.getPassword() != null && user.getEmail() != null) fictitiousUser = userNotSearchedForCreateAccount(user);

        return fictitiousUser;
    }

    /**
     * Metodo che fa la query di ricerca sul DB per cercare l'id di un utente che mi viene passato con solo username e password
     * @param user utente che mi viene passato per controllare qual è il suo id
     * @return fictitiousUser con solamente l'id dell utente con quelle date credenziali
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente con le credenziali che ha dato
     */
    private User userSearchIdBasedOnTheirUsernameAndPassword(User user) throws SQLException, UserNotFoundException {
        //creazione della query di ricerca nel DB di tipo "DBQuery" in base al suo username e password
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ID_FROM_HIS_USERNAME_PASSWORD, user.getUsername(), user.getPassword());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query
        if(rS == null || !rS.next()) throw new UserNotFoundException("");

        //se invece è stato trovato salvo l'id dell'utente appena trovato in un utente fittizio
        User idUser = new User(rS.getInt("id"));

        //se volessi restituire tutte le info dell'utente userei questo qua sotto:
        //User idUser = get(new User(rS.getInt("id")));

        querySelect.close();
        return idUser;
    }

    /**
     * Metodo che fa la query di ricerca sul DB per cercare l'id di un utente che mi viene passato con solo username e password
     * @param user utente che mi viene passato per controllare qual è il suo id
     * @return idUser con solamente l'id dell utente con quelle date credenziali
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente con le credenziali che ha dato
     */
    private User userSearchIdBasedOnTheirEmailAndPassword(User user) throws SQLException, UserNotFoundException {
        //creazione della query di ricerca nel DB di tipo "DBQuery" in base al suo username e password
        DBQuery querySelect = DatabaseUtil.getInstance().createQuery(QUERY_SELECT_ID_FROM_HIS_EMAIL_PASSWORD, user.getEmail(), user.getPassword());

        //eseguo la query
        DatabaseUtil.getInstance().executeQuery(querySelect);

        //prendo il risultato della query
        ResultSet rS = querySelect.getResultSet();
        //controllo il risultato della query
        if(rS == null || !rS.next()) throw new UserNotFoundException("");

        //se invece è stato trovato salvo l'id dell'utente appena trovato in un utente fittizio
        User idUser = new User(rS.getInt("id"));

        //se volessi restituire tutte le info dell'utente userei questo qua sotto:
        //User idUser = get(new User(rS.getInt("id")));

        querySelect.close();
        return idUser;
    }

    /**
     * Metodo che serve solo per sollevare l'eccezione nel caso in cui l'utente provi a registrarsi con le stesse credenziali
     * con le quali si era già registrato.
     * Se solleva l'eccezione di utente non trovato allora la registrazione del suo account andrà a buon fine
     * @param user utente con tutte le sue credenziali (username, email, password)
     * @return idUser con solamente l'id dell utente con quelle date credenziali (qui non mi interessa perché sto metodo serve esclusivamente per la creazione dell'account)
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente con le credenziali che ha dato
     */
    private User userNotSearchedForCreateAccount(User user) throws SQLException, UserNotFoundException {

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

        //se volessi restituire tutte le info dell'utente userei questo qua sotto:
        //User idUser = get(new User(rS.getInt("id")));

        querySelect.close();
        return idUser;
    }

}
