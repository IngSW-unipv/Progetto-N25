package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import java.sql.SQLException;

public class UsernamePassword implements UserCredentialsStrategy {
    @Override
    public User searchUser(User user) throws SQLException, UserNotFoundException {
        User userIdUsernamePassword = new User();
        userIdUsernamePassword = UserDAO.getInstance().userSearchIdBasedOnTheirUsernameAndPassword(user);
        return userIdUsernamePassword;
    }
}
