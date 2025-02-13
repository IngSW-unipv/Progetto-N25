package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class EmailPassword implements UserCredentialsStrategy {
    /**
     * Ricerco in base a email e password scambiando già le variabili
     * @param user utente fittizio che mi viene passato dalla view
     * @return un utente con le credenziali del login (in questo caso email e password) + il suo vero id preso da DB
     * @throws SQLException
     * @throws UserNotFoundException
     */
    @Override
    public User searchUser(User user) throws SQLException, UserNotFoundException {
        User userIdEmailPassword = new User();
        user.setEmail(user.getUsername());
        user.setUsername("null");
        userIdEmailPassword = UserDAO.getInstance().userSearchIdBasedOnTheirEmailAndPassword(user);
        return userIdEmailPassword;
    }
}
