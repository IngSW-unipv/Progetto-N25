package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.NotifyDAO;

public class EmptyNotifyAction implements INotifyAction{



    @Override
    public void accept() {

    }

    @Override
    public void build() {

    }

    @Override
    public void load(Notify notify) throws Exception{


    }
}
