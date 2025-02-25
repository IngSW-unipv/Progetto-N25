package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.notify.Notify;


public interface INotifyAction {


    void build();

    /*
    void load(Notify notify) throws Exception;
    void delete(Notify notify) throws Exception;
    void save(Notify notify) throws Exception;
     */


    Notify getNotify();
    void setNotify(Notify notify);
    String type();



}
