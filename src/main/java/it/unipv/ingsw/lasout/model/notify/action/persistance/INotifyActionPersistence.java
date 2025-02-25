package it.unipv.ingsw.lasout.model.notify.action.persistance;

import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

public interface INotifyActionPersistence {


    void load(Notify notifyAction) throws Exception;
    void delete(Notify notifyAction) throws Exception;
    void save(Notify notifyAction) throws Exception;
}
