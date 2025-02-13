package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import java.sql.SQLException;

/**
 * Classe strategy per discriminare il fatto che l'utente inserisca username-password o email-password
 */
public interface UserCredentialsStrategy {

    /**
     * Metodo che serve per discriminare la ricerca dell'utente inn base alle sue credenziali
     * @param user utente fittizio che mi viene passato con username-password o email-password
     * @return un utente con il suo id e le informazioni datemi dal login
     * @throws SQLException eccezione nel caso in cui la query dia errori in esecuzione
     * @throws UserNotFoundException eccezione nel caso in cui la query non trovi l'account dell'utente richiesto
     */
    public User searchUser(User user) throws SQLException , UserNotFoundException;
}
