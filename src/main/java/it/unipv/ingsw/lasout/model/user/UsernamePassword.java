package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class UsernamePassword implements UserCredentialsStrategy {
    /**
     * Ricerco l'utente in base a username e password
     * @param user utente fittizio che mi viene passato dalla view
     * @return un utente con le credenziali del login (in questo caso username e password) + il suo vero id preso da DB
     * @throws SQLException
     * @throws UserNotFoundException
     */
    @Override
    public User searchUser(User user) throws SQLException, UserNotFoundException {
        User userIdUsernamePassword = new User();
        userIdUsernamePassword = UserDAO.getInstance().userSearchIdBasedOnTheirUsernameAndPassword(user);
        System.out.println(userIdUsernamePassword);
        return userIdUsernamePassword;
    }
}
