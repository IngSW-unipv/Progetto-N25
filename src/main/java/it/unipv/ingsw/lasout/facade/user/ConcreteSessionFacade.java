package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class ConcreteSessionFacade implements ISessionFacade {
    private boolean isLoggedIn;
    private User loggedUser;

    //costruttore con loggedUser presettate a null
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


    /**
     * Metodo per il login che prende in ingresso i dati di un utente e tramite "UserDAO" restituisce un utente
     * con solo il suo id e gli modifica anche la variabile booleana per sapere se ha loggato
     * correttamente nell'applicazione
     * @param userCarrier utente fittizio con le sue credenziali: username, password ed email
     * @return l'utente con solamente l'id al quale erano associate username, email e password
     */
    public User login(User userCarrier) {
        try {
            loggedUser = UserDAO.getInstance().userSearchIdBasedOnTheirCredentials(userCarrier);
            isLoggedIn = true;
            return loggedUser;
        } catch (Exception sql) {
            return null;
        }
    }

    /**
     * Se l'utente inserito è presente nel db e ha già effettuato il login allora posso fargli fare il logout
     * @return false se
     */
    public boolean logout() {
        /*
        se l'utente che mi viene passato ha già effettuato il login (passando i vari controlli)
        allora posso fare il suo logout
         */
        if(!isLogged()) return false;
        loggedUser = null;
        return true;
    }
}
