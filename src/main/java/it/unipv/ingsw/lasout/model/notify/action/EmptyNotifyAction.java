package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;

import javax.swing.*;


public class EmptyNotifyAction implements INotifyAction{

    private Notify notify;

    private INotifyActionPersistence persistence;

    @Override
    public void build(NotifyController notifyController, JPanel buttonPanel) {

    }

    @Override
    public String type() {
        return "empty";
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
