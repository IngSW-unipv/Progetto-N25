package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import java.sql.SQLException;

public class UsernamePassword implements UserCredentialsStrategy {
    private User userIdUsernamePassword = new User();

    @Override
    public User searchUser(User user) throws SQLException, UserNotFoundException {
        userIdUsernamePassword = UserDAO.getInstance().userSearchIdBasedOnTheirUsernameAndPassword(user);
        return userIdUsernamePassword;
    }
}
