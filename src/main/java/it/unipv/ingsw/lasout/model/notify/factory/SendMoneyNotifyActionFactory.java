package it.unipv.ingsw.lasout.model.notify.factory;

import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.SendMoneyNotifyAction;

public class SendMoneyNotifyActionFactory implements AbstractNotifyActionFactory{


    @Override
    public INotifyAction create() {
        return new SendMoneyNotifyAction();
    }
}
