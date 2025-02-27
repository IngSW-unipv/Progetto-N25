package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.model.notify.Notify;

import javax.swing.*;


public class EmptyNotifyAction implements INotifyAction{

    private Notify notify;


    @Override
    public void build(NotifyController notifyController, JPanel buttonPanel) {

    }

    @Override
    public String type() {
        return "notifyempty";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
