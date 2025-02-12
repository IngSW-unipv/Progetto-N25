package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class EmailPassword implements UserCredentialsStrategy {
    public User serchUser(User user) throws SQLException, UserNotFoundException {
        User userId = new User();
        if(user.getUsername().contains("@")){
            user.setEmail(user.getUsername());
            //user.setUsername(null);
            userId= UserDAO.getInstance().userSearchIdBasedOnTheirEmailAndPassword(user);
            return userId;
        }
        return null;
    }
}
