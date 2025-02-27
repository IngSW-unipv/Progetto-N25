package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;

import javax.swing.*;


public interface INotifyAction {


    void build(NotifyController notifyController, JPanel buttonPanel);

    /*
    void load(Notify notify) throws Exception;
    void delete(Notify notify) throws Exception;
    void save(Notify notify) throws Exception;
     */

    /**
     * type è la chiave unica di ogni INotifyAction
     *
     * @return il nome che funziona da CHIAVE
     */
    String type();

}
