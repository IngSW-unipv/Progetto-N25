package it.unipv.ingsw.lasout.facade.user;

import it.unipv.ingsw.lasout.model.user.User;

import java.sql.SQLException;

public interface IUserFacade {

    boolean createAccount(User userCarrier) throws SQLException;
    boolean deleteAccount(User userCarrier) throws SQLException;
    boolean updateAccount(User userCarrier) throws SQLException;

}
