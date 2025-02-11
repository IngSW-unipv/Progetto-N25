package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class UsernamePassword implements UserCredentialsStrategy {
    @Override
    public User serchUser(User user) throws SQLException, UserNotFoundException {
        User userId = new User();
        if(!user.getUsername().contains("@")){
            userId=UserDAO.getInstance().userSearchIdBasedOnTheirUsernameAndPassword(user);
            return userId;
        }
        return null;
    }
}
