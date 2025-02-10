package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class ConcreteUserFacade implements IUserFacade {

    /**
     * Metodo di aggiunta dell'account di un utente al DB
     * @param userCarrier utente fittizio con le sue credenziali: username, password ed email
     */
    @Override
    public boolean createAccount(User userCarrier) {
        User u= new User();
        try{
            /*
            faccio questo if perché poi almeno lo user sa se entrare nel metodo di ricerca con
            username-password oppure email-password (per discriminare le due cose)
             */
            if(userCarrier.getUsername()==null && userCarrier.getEmail()!=null) userCarrier.setInsertEmail(true);

            /*
            cerco che l'utente che mi è stato passato NON sia già presente nel DB, almeno lancerà un eccezione
            di "utente non trovato" che verrà presa dal catch(UserNotFoundException e) e solo dopo esserci entrato
            potrò salvare il nuovo account dell'utente nel DB
             */
            u = UserDAO.getInstance().userSearchIdBasedOnTheirCredentials(userCarrier);
            System.out.println("Utente già presente nel sistema, metti un'altro username!");
            return false;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }catch (UserNotFoundException e){
            //serve perché la "save" può lanciare un eccezione sulla query che devo gestire qua
            try {
                UserDAO.getInstance().save(userCarrier);
                return true;
            }catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
            }
        }
        return false;
    }


    /**
     * Metodo di eliminazione dell'account di un certo utente
     * @param userCarrier
     *
     * TODO 1: Il sistema chiede all’utente su quale conto\carta inviare tutto il denaro del suo Vault
     *         Il sistema verifica che le informazioni siano valide
     *         Le informazioni vengono approvate
     * TODO 2: è un problema se non si riaggiornano tutti gli indici dell'utente? e quindi eliminando un utente rimangano dei buchi di numeri (elimino dema(id=3) e gli indici saranno poi 1 2 4 5 6)
     */
    @Override
    public boolean deleteAccount(User userCarrier) {
        //utente di appoggio che serve a contenere solo l'id dell'utente del quale si vuole eliminare l'account
        User u = new User();

        try{
            /*
            faccio questo if perché poi almeno lo user sa se entrare nel metodo di ricerca con
            username-password oppure email-password (per discriminare le due cose)
             */
            if(userCarrier.getUsername()==null && userCarrier.getEmail()!=null) userCarrier.setInsertEmail(true);

            u = UserDAO.getInstance().userSearchIdBasedOnTheirCredentials(userCarrier);
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){
                /*
                dato che il metodo delete cancella la tupla controllando l'id di un certo user devo andare a prendere l'id
                dell'utente vero e proprio con le sue credenziali che mi viene passato (userCarrier)
                 */
                UserDAO.getInstance().delete(u);
                LaVaultFacade.getInstance().getSessionFacade().logout();
                return true;
            }
        }catch (UserNotFoundException e){
            System.out.println("Utente non trovato, impossibile eliminare l'account");
            return false;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }


    /**
     * Metodo che aggiorna la password di un utente che è già registrato nell'applicazione
     * @param userCarrier utente con le sue credenziali (username, email e password)
     * @param newPassword nuova password che l'utente vuole aggiornare dell'account
     * @return true se la password è stata cambiata correttamente (false per gli altri casi in cui non si è riusciti a modificarla)
     */
    @Override
    public boolean updateAccount(User userCarrier, String newPassword){
        User u = new User();
        try{
            /*
            faccio questo if perché poi almeno lo user sa se entrare nel metodo di ricerca con
            username-password oppure email-password (per discriminare le due cose)
             */
            if(userCarrier.getUsername()==null && userCarrier.getEmail()!=null) userCarrier.setInsertEmail(true);

            //controlla che l'utente sia registrato e restituisce un utenteFittizio col suo id
            u = UserDAO.getInstance().userSearchIdBasedOnTheirCredentials(userCarrier);
            //modifico la password di questo utenti fittizio (quindi ora avrà l'id dell'utente originale e la nuova password)
            u.setPassword(newPassword);
            //System.out.println(u);
            //solo dopo esser stato trovato nel DB l'utente può aggiornare la sua password
            UserDAO.getInstance().update(u);
            return true;
        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

}
