package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.controller.notify.NotifyController;

import javax.swing.*;


public interface INotifyAction {


    void build(NotifyController notifyController, JPanel buttonPanel);

    /*
    void load(Notify notify) throws Exception;
    void delete(Notify notify) throws Exception;
    void save(Notify notify) throws Exception;
     */

    String type();



}
