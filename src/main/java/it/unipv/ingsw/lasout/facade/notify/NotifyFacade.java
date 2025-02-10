package it.unipv.ingsw.lasout.facade.notify;

import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.User;

public interface NotifyFacade {





    void sendNotify(Notify notify);


    void sendSystemNotify(User user, String message);


    void sendFriendRequest(User from, User  to);
}
