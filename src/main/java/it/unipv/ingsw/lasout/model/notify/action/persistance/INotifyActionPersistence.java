package it.unipv.ingsw.lasout.model.notify.action.persistance;

import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

public interface INotifyActionPersistence {


    void load(INotifyAction notify) throws Exception;
    void delete(INotifyAction notify) throws Exception;
    void save(INotifyAction notify) throws Exception;
}
