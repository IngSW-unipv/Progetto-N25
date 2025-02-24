package it.unipv.ingsw.lasout.model.notify.action.friend;

import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

public class SendMoneyNotifyAction implements INotifyAction {

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
        return "notifysendmoney";
    }
}
