package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.model.user.exception.UserAlreadyExistException;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;

public class ConcreteUserFacade implements IUserFacade {
    private User loggedUser;

    //createAccount,deleteAccount,updateAccount?

    /**
     * Metodo di aggiunta dell'account di un utente al DB
     * @param userCarrier utente fittizio con le sue credenziali: username, password ed email
     */
    public boolean createAccount(User userCarrier) throws SQLException {

        try{
            if(LaVaultFacade.getInstance().getSessionFacade().login(userCarrier)==null){
                UserDAO.getInstance().save(userCarrier);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    //TODO controlli vari per l'eliminazione di un certo utente
    /**
     * Metodo di eliminazione dell'account di un certo utente
     * @param userCarrier
     *
     * TODO Il sistema chiede all’utente su quale conto\carta inviare tutto il denaro del suo Vault
     *      Il sistema verifica che le informazioni siano valide
     *      Le informazioni vengono approvate
     */
    public boolean deleteAccount(User userCarrier) throws SQLException{
        User u;

        try{
            /*
            if(LaVaultFacade.getInstance().getSessionFacade().login(userCarrier)==null){
                u=LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
                UserDAO.getInstance().delete(u);
                return true;
            }

             */
            UserDAO.getInstance().delete(userCarrier);
        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    //TODO controlli vari per l'update di un certo utente
    public boolean updateAccount(User userCarrier) throws SQLException{
        try{
            UserDAO.getInstance().update(userCarrier);
            return true;
        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
