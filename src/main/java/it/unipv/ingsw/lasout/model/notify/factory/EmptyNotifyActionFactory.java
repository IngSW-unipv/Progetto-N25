package it.unipv.ingsw.lasout.model.notify.factory;

import it.unipv.ingsw.lasout.model.notify.action.EmptyNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

public class EmptyNotifyActionFactory implements AbstractNotifyActionFactory{

    @Override
    public INotifyAction create() {
        return new EmptyNotifyAction();
    }
}
