package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ConcreteSessionFacade implements SessionFacade {
    private User loggedUser;
    private boolean isLogged=false;

    //costruttore con variabili presettate
    public ConcreteSessionFacade() {
        loggedUser = null;
        isLogged=false;
    }

    @Override
    public boolean isLogged() {
        return isLogged;
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }


    /**
     *
     * @param userCarrier
     */
    public void CreateAccount(User userCarrier){
        try {
            //se l'utente che ha inserito i dati non è presente nel DB allora posso aggiungerlo
            if(Login(userCarrier) == null){
                UserDAO.getInstance().save(userCarrier);
            }else{
                System.out.println("L'utente: "+userCarrier.getUsername()+" è già presente. Impossibile creare un account");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Metodo per il login che prende in ingresso i dati di un utente e tramite "UserDAO" restituisce un utente
     * con solo il suo id e gli modifica anche la variabile booleana per sapere se ha loggato
     * correttamente nell'applicazione
     * @param userCarrier utente fittizio con solo username e password e controllo
     * @return l'utente con solamente l'id al quale erano associate username, email e password
     */
    public User Login(User userCarrier) {
        try {
            loggedUser = UserDAO.getInstance().userSearchIdBasedOnTheirCredentials(userCarrier);
            isLogged = true;
            return loggedUser;
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
            isLogged = false;
            loggedUser = null;
            return null;
        }catch (UserNotFoundException userNotFound) {
            System.out.println(userNotFound.getMessage());
            isLogged = false;
            loggedUser = null;
            return null;
        }
    }

    /**
     * Se l'utente inserito è presente nel db e ha già effettuato il login allora posso fargli fare il logout
     *
     * @param userCarrier
     * @return
     */
    public boolean Logout(User userCarrier) {
        /*
        se l'utente che mi viene passato ha già fatto il login (passando i vari controlli)
        allora posso fare il suo logout
         */
        if(Login(userCarrier) != null && isLogged){
            loggedUser = null;
            return isLogged = false;
        }else{
            return isLogged = true;
        }
    }


    public void DeleteAccount(User userCarrier){
        try{
            if(Login(userCarrier) != null && isLogged){
                UserDAO.getInstance().delete(userCarrier);
            }
        }catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }

    }
}
