package it.unipv.ingsw.lasout.model.user;

public class UserFacade {
    private static UserFacade instance;
    public static UserFacade getInstance() {
        if (instance == null){
            instance = new UserFacade();
        }
        return instance;
    }

    public boolean newUser(User user) {
        try{
            UserDAO.getInstance().save(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean getUser(User user) {
        try{
            UserDAO.getInstance().get(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }

}
