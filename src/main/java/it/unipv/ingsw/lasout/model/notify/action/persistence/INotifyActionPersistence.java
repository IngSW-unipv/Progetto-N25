package it.unipv.ingsw.lasout.model.notify.action.persistence;

import it.unipv.ingsw.lasout.model.notify.Notify;

public interface INotifyActionPersistence {


    void load(Notify notifyAction) throws Exception;
    void delete(Notify notifyAction) throws Exception;
    void save(Notify notifyAction) throws Exception;
}
