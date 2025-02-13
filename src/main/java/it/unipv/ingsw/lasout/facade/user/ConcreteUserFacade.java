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


    @Override
    public boolean updatePassword(User userCarrier){
        Scanner scanner = new Scanner(System.in);
        String newPassword;

        try{
            //solo dopo esser stato trovato nel DB e aver effettuato il login, l'utente può aggiornare la sua password
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){
                //chiedo quale password vuole inserire per sovrascrivere la vecchia
                System.out.println("New password is?: ");
                newPassword = scanner.nextLine();
                userDAO.updatePassword(userCarrier, newPassword);
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
