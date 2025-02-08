package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.database.DatabaseUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class UserFacade {
    private static UserFacade instance;
    public static UserFacade getInstance() {
        if (instance == null){
            instance = new UserFacade();
        }
        return instance;
    }

    public void createAccount(User user) {
        //int iD=0;
        try {
            //iD = UserDAO.getInstance().saveUser(user);
            //TODO controllo se l'utente c'è già
            UserDAO.getInstance().save(user);
        } catch (Exception e) {
            //throws new Exception("Errore nella persistencia del usuario");
        }
    }

    /**
     * Controlla se le credenziali (username e password) che gli sono state passate sono effettivamente presenti stato trovato nel dB oppure no.
     * Se è presente allora il login è effettuato correttamente
     * Se
     * @param userCarrier utente fittizio con solo username e password e controllo
     * @return
     */
    public User isLoggedIn(User userCarrier) {
        User u=null;
        try {
            u=UserDAO.getInstance().userSearchBasedOnTheirCredentials(userCarrier);
            return u;
        } catch (Exception e) {
            System.out.println("L'utente " + userCarrier.getUsername() + " non è stato trovato");
            //restituisco 0 se l'utente non è stato trovato nel db
            return null;
        }
    }





    //main di test dei metodi
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    //main di test
    public static void main(String[] args) throws Exception {
        //controllo iniziale per il DB
        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Can't initialize the DB: \n" + e);
            System.exit(1);
            return;
        }

        //test 1
        User user = UserDAO.getInstance().getRaw(new User(1));
        System.out.println(user);

        UserDAO.getInstance().save(new User("cicco","palla","ciao@gmail.com"));
        UserDAO.getInstance().save(new User(13,"malloni","palla","ciao@gmail.com"));
        UserDAO.getInstance().delete(new User("malloni","palla","ciao@gmail.com"));

        ArrayList<User> users = UserDAO.getInstance().getAll();
        System.out.println("\nTUTTI I BRO:\n");
        for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i));
        }

        //test 2
        User userCarrier = new User("dada","ciao","aaa@hotmail.com");
        System.out.println(UserFacade.getInstance().isLoggedIn(userCarrier));
    }

}
