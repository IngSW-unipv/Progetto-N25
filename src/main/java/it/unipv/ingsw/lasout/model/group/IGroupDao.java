package it.unipv.ingsw.lasout.model.group;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public interface IGroupDao extends IDao<Group> {

    List<User> members(Group group) throws Exception;
    void deleteAssociation(Group group) throws Exception;
    void saveAssociation(Group group) throws Exception;

}