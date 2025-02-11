package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.notify.Notify;
import javafx.scene.layout.HBox;

public class SendMoneyNotifyAction implements INotifyAction{

    private Notify notify;

    @Override
    public void build(HBox hBox, HBox box) {

    }

    @Override
    public void load(Notify notify) throws Exception {

    }

    @Override
    public void delete(Notify notify) throws Exception {

    }

    @Override
    public void save(Notify notify) throws Exception {

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
