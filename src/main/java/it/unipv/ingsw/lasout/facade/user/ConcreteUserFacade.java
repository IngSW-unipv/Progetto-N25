package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.model.user.*;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class ConcreteUserFacade implements IUserFacade {

    private final IUserDAO userDAO;

    public ConcreteUserFacade() {
        userDAO=DaoFactory.getUserDAO();
    }

    /**
     * Metodo di aggiunta dell'account di un utente al DB.
     * Cerco che l'utente NON sia già presente nel DB, almeno lancerà un eccezione
     * di "utente non trovato" che verrà presa dal catch(UserNotFoundException e) e solo dopo esserci entrato
     * potrò salvare il nuovo account dell'utente nel DB
     * @param userCarrier utente fittizio con le sue credenziali: username, password ed email
     */
    @Override
    public boolean createAccount(User userCarrier) {
        try{
            userDAO.userNotSearchedForCreateAccount(userCarrier);
            System.out.print("User already exist, impossible to CREATE your account. Please use another username.");
            return false;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }catch (UserNotFoundException e){
            //serve perché la "save" può lanciare un eccezione sulla query che devo gestire qua
            try {
                userDAO.save(userCarrier);
                return true;
            }catch (Exception sqlException){
                System.out.println(sqlException.getMessage());
                return false;
            }
        }
    }


    /**
     * Metodo di eliminazione dell'account di un certo utente.
     * Cerco l'utente con logica di discriminazione di username-password o email-password e controllo che
     * sia presente nel DB almeno posso cancellare il suo account.
     * Dato che il metodo delete cancella la tupla interessandosi solo dell'id di un certo user devo andare a prendere l'id
     * dell'utente vero e proprio con le sue credenziali che mi viene passato (userCarrier)
     * @param userCarrier utente passatomi
     *
     * TODO 1: Il sistema chiede all’utente su quale conto\carta inviare tutto il denaro del suo Vault
     *         Il sistema verifica che le informazioni siano valide
     *         Le informazioni vengono approvate
     * TODO 2: è un problema se non si riaggiornano tutti gli indici dell'utente? e quindi eliminando un utente rimangano dei buchi di numeri (elimino dema(id=3) e gli indici saranno poi 1 2 4 5 6)
     */
    @Override
    public boolean deleteAccount(User userCarrier) {
        Scanner scanner = new Scanner(System.in);
        String conferma;

        try{
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){
                System.out.println("Write: 'Sono sicuro' if you want to delete this account: ");
                conferma = scanner.nextLine();
                if(conferma.equals("Sono sicuro")){
                    userDAO.delete(userCarrier);
                    System.out.println("Account successfully deleted");
                    LaVaultFacade.getInstance().getSessionFacade().logout();
                    return true;
                }else{
                    System.out.println("ERRORE, impossible to delete your account");
                    return false;
                }

            }else System.out.println("User has never done the login");

        }catch (UserNotFoundException e){
            System.out.println("User not found, impossible to DELETE your account");
            return false;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    /**
     * Metodo che aggiorna la password di un utente che è già registrato nell'applicazione.
     * Cerco l'utente con logica di discriminazione di username-password o email-password e controllo che
     * sia presente nel DB almeno posso modificargli la password.
     * @param userCarrier utente con le sue credenziali (username, email e password)
     * @param newPassword nuova password che l'utente vuole aggiornare dell'account
     * @return true se la password è stata cambiata correttamente (false per gli altri casi in cui non si è riusciti a modificarla)
     */
    @Override
    public boolean updateAccount(User userCarrier, String newPassword){
        try{
            //solo dopo esser stato trovato nel DB e aver effettuato il login, l'utente può aggiornare la sua password
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){
                userDAO.update(userCarrier, newPassword);
                //modifico la password di loggedUser almeno la vedrò aggiornata se facessi getLoggedUser dopo questo update
                LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().setPassword(newPassword);
                return true;
            }else{
                System.out.println("User has never done the login");
                return false;
            }
        }catch (UserNotFoundException e){
            System.out.println("User not found, impossible to UPDATE your account");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
