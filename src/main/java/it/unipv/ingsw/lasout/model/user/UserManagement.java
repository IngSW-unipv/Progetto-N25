package it.unipv.ingsw.lasout.model.user;

public class UserManagement {
    private UserDAO userDAO;
    private User loggedUser;

    //costruttore
    public UserManagement(UserDAO user) {
        this.userDAO = userDAO;
    }


}
