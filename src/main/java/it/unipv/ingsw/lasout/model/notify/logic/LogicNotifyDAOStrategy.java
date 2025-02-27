package it.unipv.ingsw.lasout.model.notify.logic;

import it.unipv.ingsw.lasout.model.notify.INotifyDAO;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistenceFactory;

public interface LogicNotifyDAOStrategy extends INotifyDAO {

    INotifyActionPersistenceFactory getPersistenceFactory();
}
