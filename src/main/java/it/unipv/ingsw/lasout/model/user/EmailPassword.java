package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import java.sql.SQLException;

public class EmailPassword implements UserCredentialsStrategy {
    private User userIdEmailPassword = new User();

    @Override
    public User searchUser(User user) throws SQLException, UserNotFoundException {
        user.setEmail(user.getUsername());
        user.setUsername("null");
        userIdEmailPassword = UserDAO.getInstance().userSearchIdBasedOnTheirEmailAndPassword(user);
        return userIdEmailPassword;
    }
}
