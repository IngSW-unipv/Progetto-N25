package it.unipv.ingsw.lasout.controller.notify;

import it.unipv.ingsw.lasout.model.notify.Notify;

import java.awt.event.ActionEvent;

public class ButtonNotifyAction extends ActionEvent {

    private Notify notify;

    public ButtonNotifyAction(Notify notify, Object source, int id, String command) {
        super(source, id, command);
        this.notify = notify;
    }

    public Notify getNotify() {
        return notify;
    }
}
