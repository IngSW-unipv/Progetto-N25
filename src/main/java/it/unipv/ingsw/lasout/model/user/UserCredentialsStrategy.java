package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

/**
 * Classe strategy per discriminare il fatto che l'utente inserisca username-password o email-password
 */
public interface UserCredentialsStrategy {
    /**
     * Metodo che serve per discriminare questa cosa
     * @param user utente fittizio che mi viene passato dalla view
     * @return un utente con solamente il suo id
     * @throws SQLException
     * @throws UserNotFoundException
     */
    public User serchUser(User user) throws SQLException , UserNotFoundException;
}
