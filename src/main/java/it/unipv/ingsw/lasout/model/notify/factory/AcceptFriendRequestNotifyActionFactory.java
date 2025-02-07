package it.unipv.ingsw.lasout.model.notify.factory;

import it.unipv.ingsw.lasout.model.notify.action.AcceptFriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

public class AcceptFriendRequestNotifyActionFactory implements AbstractNotifyActionFactory{


    @Override
    public INotifyAction create() {
        return new AcceptFriendRequestNotifyAction();
    }
}
