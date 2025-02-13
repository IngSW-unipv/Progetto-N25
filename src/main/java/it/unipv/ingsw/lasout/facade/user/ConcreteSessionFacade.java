package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.EmailPassword;
import it.unipv.ingsw.lasout.model.user.UserCredentialsStrategy;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UsernamePassword;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class ConcreteSessionFacade implements ISessionFacade {
    private boolean isLoggedIn;
    private User loggedUser;

    /**
     * COSTRUTTORE con variabili presettate di default
     */
    public ConcreteSessionFacade() {
        isLoggedIn = false;
        loggedUser = null;
    }


    @Override
    public boolean isLogged() {
        return isLoggedIn;
    }


    @Override
    public User getLoggedUser() {
        return loggedUser;
    }


    @Override
    public void login(User userCarrier) {
        try {
            UserCredentialsStrategy userCredentialsStrategy;
            if (userCarrier.getUsername().contains("@")) userCredentialsStrategy = new EmailPassword();
            else userCredentialsStrategy = new UsernamePassword();

            /*
            usando un il factory:
            UserCredentialsStrategy userCredentialsStrategy = UserCredentialsFactory.createUserCredentials(userCarrier);
             */

            this.loggedUser = userCredentialsStrategy.searchUser(userCarrier);
            //System.out.println("LOGGED USER-->" + loggedUser); //username con credenziali di login + il suo id
            this.isLoggedIn = true;
        } catch (UserNotFoundException e) {
            System.out.println("User not found");
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
        }
    }


    @Override
    public void logout() {
        /*
        se l'utente che mi viene passato ha già effettuato il serchUser (passando i vari controlli)
        allora posso fare il suo logout
         */
        if(isLogged()){
            isLoggedIn = false;
            loggedUser = null;
        }
        else{
            isLoggedIn = true;
            System.out.println("User has never done the login");
        }
    }
}
