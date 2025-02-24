package it.unipv.ingsw.lasout.model.notify.factory;

import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

public class FriendRequestNotifyActionFactory implements AbstractNotifyActionFactory{


    @Override
    public INotifyAction create() {
        return new FriendRequestNotifyAction();
    }
}
