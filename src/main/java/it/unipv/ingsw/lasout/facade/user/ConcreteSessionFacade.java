package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

public class ConcreteSessionFacade implements ISessionFacade {
    private boolean isLoggedIn;
    private User loggedUser;

    //costruttore con loggedUser presettate
    public ConcreteSessionFacade() {
        isLoggedIn = false;
        loggedUser = null;
    }

    /**
     * Metodo che restituisce un true se l'utente è loggato e false se non ha fatto prima il login
     * @return isLoggedIn
     */
    @Override
    public boolean isLogged() {
        return isLoggedIn;
    }

    /**
     * Metodo che restituisce uno user con solamente il suo id e gli altri campi a null
     * @return loggedUser: user con id
     */
    @Override
    public User getLoggedUser() {
        return loggedUser;
    }


    /**
     * Metodo per il login che prende in ingresso i dati di un utente e tramite "UserDAO":
     * 1) setta un utente (loggedUser) con solo il suo id;
     * 2) modifica la variabile booleana (true se ha loggato correttamente; false (settata di default dal costruttore) negli altri casi)
     * @param userCarrier utente fittizio con le sue credenziali: username, password ed email
     */
    @Override
    public void login(User userCarrier) {
        try {
            /*
            faccio questo if perché poi almeno lo user sa se entrare nel metodo di ricerca con
            username-password oppure email-password (per discriminare le due cose)
             */
            if(userCarrier.getUsername()==null && userCarrier.getEmail()!=null) userCarrier.setInsertEmail(true);

            loggedUser = UserDAO.getInstance().userSearchIdBasedOnTheirCredentials(userCarrier);
            isLoggedIn = true;
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
        }
    }

    /**
     * Metodo che slogga l'utente inserito se e solo se è presente nel db e ha già effettuato il login allora posso fargli fare il logout
     */
    @Override
    public void logout() {
        /*
        se l'utente che mi viene passato ha già effettuato il login (passando i vari controlli)
        allora posso fare il suo logout
         */
        if(isLogged()){
            isLoggedIn = false;
            loggedUser = null;
        }
        else{
            isLoggedIn = true;
            System.out.println("User non ha fatto prima il login");
        }
    }
}
