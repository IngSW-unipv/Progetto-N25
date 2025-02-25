package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.notify.Notify;


public class EmptyNotifyAction implements INotifyAction{

    private Notify notify;


    @Override
    public void build() {

    }

    @Override
    public Notify getNotify() {
        return notify;
    }

    @Override
    public void setNotify(Notify notify) {
        this.notify = notify;
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
