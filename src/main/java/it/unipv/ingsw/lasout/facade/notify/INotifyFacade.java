package it.unipv.ingsw.lasout.facade.notify;

import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.Collection;

public interface INotifyFacade {



    boolean sendNotify(Notify notify);


    boolean sendSystemNotify(User user, String message);


    boolean sendFriendRequest(User from, User  to);

    boolean sendPayRequestByUser(User from, User to, double value);
    boolean sendPayRequestByGroup(Group from, User to, double value);

    boolean sendInviteGroupRequest(Group from, User to);

    Collection<Notify> getAll(User loggedUser) throws Exception;

    boolean delete(Notify notify);
}
