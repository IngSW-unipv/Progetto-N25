package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.User;

public interface IUserFacade {

    boolean createAccount(User userCarrier);
    boolean deleteAccount(User userCarrier);
    boolean updateAccount(User userCarrier,String newPassword);

}
