package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO extends IDao<User> {

    /**
     * Metodo di update della password di un certo utente identificato tramite il suo id
     * @param user utente del quale voglio modificare la password
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente
     */
    void updatePassword(User user, String newPassword) throws SQLException, UserNotFoundException;

    /**
     * Metodo che serve ad associare una lista di amici (altri utenti) a un certo utente
     * @param user utente che avrà una determinata lista di amici
     * @return la lista di amici dell'utente
     */
    List<User> getFriends(User user);

    /**
     * Metodo che serve ad associare la lista di tutte le notifiche a un determinato utente
     * @param user utente che avrà una determinata lista di notifiche
     * @return la lista delle notifiche
     * @throws Exception eccezione lanciata dalla classe NotifyDAO
     */
    List<Notify> getNotifications(User user) throws Exception;

    /**
     * Metodo che serve ad associare una lista di gruppi (altri utenti) a un certo utente
     * @param user utente che avrà una determinata lista di gruppi ai quali partecipa
     * @return la lista di gruppi dell'utente
     * @throws Exception eccezione lanciata dalla classe GroupDAO
     */
    List<Group> groupsOfUser(User user) throws SQLException, UserNotFoundException;

    /**
     * Metodo che fa la query di ricerca sul DB per cercare l'id di un utente che mi viene passato con solo username e password
     * @param userCarrier utente che mi viene passato
     * @return l'utente con id + tutte le sue informazioni
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente con le credenziali che ha dato
     */
    User userSearchIdBasedOnTheirUsernameAndPassword(User userCarrier) throws SQLException, UserNotFoundException;

    /**
     * Metodo che fa la query di ricerca sul DB per cercare l'id di un utente che mi viene passato con solo email e password
     * @param userCarrier utente che mi viene passato
     * @return l'utente con l'id + tutte le sue informazioni
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente con le credenziali che ha dato
     */
    User userSearchIdBasedOnTheirEmailAndPassword(User userCarrier) throws SQLException, UserNotFoundException;

    /**
     * Metodo che serve solo per sollevare l'eccezione nel caso in cui l'account dell'utente esiste già.
     * Se solleva l'eccezione di "utente non trovato" allora la registrazione del suo account andrà a buon fine
     * @param user utente con tutte le sue credenziali (username, email, password)
     * @return l'utente con solo il suo id (perché verrà usato dalla userDao.save per aggiungerlo al DB)
     * @throws SQLException eccezione nel caso in cui la query non vada a buon fine
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente con le credenziali che ha dato
     */
    User userNotSearchedForCreateAccount(User user) throws SQLException, UserNotFoundException;
}
