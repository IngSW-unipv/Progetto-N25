package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.EmailPassword;
import it.unipv.ingsw.lasout.model.user.UserCredentialsStrategy;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UsernamePassword;

public class UserCredentialsFactory {



    public static UserCredentialsStrategy createUserCredentials(User user){
        return user.getUsername() != null ? user.getUsername().contains("@")  ? new EmailPassword() : new UsernamePassword() : null;
    }
}
