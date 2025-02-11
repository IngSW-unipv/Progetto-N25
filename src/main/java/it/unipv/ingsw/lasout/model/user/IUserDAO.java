package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO extends IDao<User> {
    List<User> getFriends(User user);

    List<Notify> getNotifications(User user) throws Exception;

    List<Group> groupsOfUser(User user) throws SQLException, UserNotFoundException;

    User userSearchIdBasedOnTheirCredentials(User user) throws SQLException, UserNotFoundException;

    User userSearchIdBasedOnTheirUsernameAndPassword(User user) throws SQLException, UserNotFoundException;

    User userSearchIdBasedOnTheirEmailAndPassword(User user) throws SQLException, UserNotFoundException;

    User userNotSearchedForCreateAccount(User user) throws SQLException, UserNotFoundException;

}
