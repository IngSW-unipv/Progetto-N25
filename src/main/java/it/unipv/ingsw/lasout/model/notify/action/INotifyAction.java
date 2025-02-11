package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.notify.Notify;
import javafx.scene.layout.HBox;


public interface INotifyAction {


    void build(HBox information, HBox footer);

    void load(Notify notify) throws Exception;
    void delete(Notify notify) throws Exception;
    void save(Notify notify) throws Exception;

    Notify getNotify();
    void setNotify(Notify notify);
    String type();



}
