package it.unipv.ingsw.lasout.model.notify.action.factory;

import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;

public interface INotifyActionFactory {


    //void injectPersistence(INotifyAction action);
    INotifyAction get(String notify);
    //INotifyAction getUnInjected(String notify);
    INotifyActionPersistence getPersistence(String notify);
}
