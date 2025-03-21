package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.user.*;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConcreteUserFacade implements IUserFacade {

    private final IUserDAO userDAO;

    public ConcreteUserFacade() {
        userDAO=DaoFactory.getUserDAO();
    }

    @Override
    public boolean createAccount(User userCarrier) {
        try{
            userDAO.userNotFoundForCreateAccount(userCarrier);
            System.out.print("User already exist, impossible to CREATE your account. Please use another username.");
            return false;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }catch (UserNotFoundException e){
            //serve perché la "save" può lanciare un eccezione sulla query che devo gestire qua
            try {
                userDAO.save(userCarrier);

                // inizializzazione all'iscrizione
                initOnSignIn(userCarrier);
                
                initOnSignInVault(userCarrier);

                return true;
            }catch (Exception sqlException){
                System.out.println(sqlException.getMessage());
                return false;
            }
        }
    }

    private void initOnSignIn(User userCarrier) {
        User allUserInformation = new User();
        try{
            UserCredentialsStrategy userCredentialsStrategy;
            if (userCarrier.getUsername().contains("@")) userCredentialsStrategy = new EmailPassword();
            else userCredentialsStrategy = new UsernamePassword();

            allUserInformation = userCredentialsStrategy.searchUser(userCarrier);

            LaVaultFacade.getInstance().getCashbookFacade().createDefaultCashbook(allUserInformation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void initOnSignInVault(User userCarrier) {
        User allUserInformation = new User();
        try{
            UserCredentialsStrategy userCredentialsStrategy;
            if (userCarrier.getUsername().contains("@")) userCredentialsStrategy = new EmailPassword();
            else userCredentialsStrategy = new UsernamePassword();

            allUserInformation = userCredentialsStrategy.searchUser(userCarrier);

            LaVaultFacade.getInstance().getVaultFacade().newVaultinVirtualVault(allUserInformation);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public boolean deleteAccount(User userCarrier) {
        try{
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){
                userDAO.delete(userCarrier);
                System.out.println("Account successfully deleted");
                LaVaultFacade.getInstance().getSessionFacade().logout();
                return true;
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
    public boolean updatePassword(User userCarrier, String newPassword){


        try{
            //solo dopo esser stato trovato nel DB e aver effettuato il login, l'utente può aggiornare la sua password
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){
                /*
                //chiedo quale password vuole inserire per sovrascrivere la vecchia
                Scanner scanner = new Scanner(System.in);
                System.out.println("New password is?: ");
                String newPassword = scanner.nextLine();
                 */
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

    @Override
    public List<Group> getGroupOfLoggedUser(){
        List<Group> groups;
        try {
            groups= userDAO.groupsOfUser(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public User getUser(User userCarrier) {
        try {
            return userDAO.getRaw(userCarrier);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Cashbook> getCashbookOfLoggedUser(){
        List<Cashbook> cashbooks = null;
        try {
            cashbooks = userDAO.getCashbooks(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cashbooks;
    }

    @Override
    public boolean deleteFriendShip(User friend, User  of) {
        try{
            userDAO.deleteFriend(friend, of);
            userDAO.deleteFriend(of, friend);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public User getUserByName(User userName) {

        try{
            return userDAO.getUserByUsername(userName);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void makeFriendShipOneWay(User from, User to) {

        try{
            userDAO.saveFriend(to, from);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public List<User> getFriends(User user) {
        try {
            return userDAO.getFriends(user);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

}
