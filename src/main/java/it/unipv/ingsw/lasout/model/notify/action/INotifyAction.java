package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.notify.Notify;

public interface INotifyAction {


    void accept();
    void build();
    void load(Notify notify) throws Exception;

    String type();
}
