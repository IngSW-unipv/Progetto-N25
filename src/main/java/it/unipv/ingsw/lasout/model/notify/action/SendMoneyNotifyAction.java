package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.notify.Notify;
import javafx.scene.layout.HBox;

public class SendMoneyNotifyAction implements INotifyAction{




    @Override
    public void accept() {

    }

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
    public String type() {
        return "notifysendmoney";
    }
}
