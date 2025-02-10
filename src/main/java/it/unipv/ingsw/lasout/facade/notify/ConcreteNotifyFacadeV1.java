package it.unipv.ingsw.lasout.facade.notify;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.group.IGroupDao;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.Properties;

public class ConcreteNotifyFacadeV1 implements NotifyFacade{


    @Override
    public void sendNotify(Notify notify) {

    }

    @Override
    public void sendSystemNotify(User user, String message) {

    }

    @Override
    public void sendFriendRequest(User from, User to) {

    }
}
