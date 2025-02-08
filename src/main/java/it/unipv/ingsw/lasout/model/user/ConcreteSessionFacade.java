package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.database.DatabaseUtil;

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
            if(Login(userCarrier)==null){
                UserDAO.getInstance().save(userCarrier);
            }else{
                System.out.println("L'utente: "+userCarrier.getUsername()+" è già presente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteAccount(User userCarrier){
        try {
            //se l'utente che ha inserito i dati non è presente nel DB allora posso eliminarlo
            if(Login(userCarrier)!=null){
                UserDAO.getInstance().delete(userCarrier);
            }else{
                System.out.println("L'utente: "+userCarrier.getUsername()+userCarrier.getPassword()+userCarrier.getEmail()+" non è presente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     *
     * @param userCarrier utente fittizio con solo username e password e controllo
     * @return l'utente con solamente l'id al quale erano associate username, email e password
     */
    public User Login(User userCarrier) {

        try {
            loggedUser=UserDAO.getInstance().userSearchIdBasedOnTheirCredentials(userCarrier);
            isLogged=true;
            return loggedUser;
        } catch (Exception e) {
            //System.out.println("L'utente " + userCarrier.getUsername() + " non è stato trovato");
            //restituisco 0 se l'utente non è stato trovato nel db
            System.out.println(e.getMessage());
            isLogged=false;
            loggedUser=null;
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
        //UserDAO.getInstance().save(new User(13,"malloni","palla","ciao@gmail.com"));
        UserDAO.getInstance().delete(new User("malloni","palla","ciao@gmail.com"));

        ArrayList<User> users = UserDAO.getInstance().getAll();
        System.out.println("\nTUTTI I BRO:\n");
        for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i));
        }

        //test 2
        User userCarrier = new User("dada","ciao","aaa@hotmail.com");
        System.out.println("\nLogged user:\n"+new ConcreteSessionFacade().Login(userCarrier)+"\n");

        //test di creazione asccount
        User addableUser = new User("pingopongo","pongopingo","aaa@hotmail.com");
        new ConcreteSessionFacade().CreateAccount(addableUser);

        User notaddableUser = new User("dema","bau","ccc@gmail.com");
        new ConcreteSessionFacade().CreateAccount(notaddableUser);

        //test di cancellazione account
        /*
        User deletableUser = new User("pingopongo","pongopingo","aaa@hotmail.com");
        new ConcreteSessionFacade().CreateAccount(addableUser);

        User notdelitableUser = new User("dema","bau","ccc@gmail.com");
        new ConcreteSessionFacade().CreateAccount(notaddableUser);
        */

    }
}
