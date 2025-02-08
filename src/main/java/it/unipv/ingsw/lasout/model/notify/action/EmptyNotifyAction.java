package it.unipv.ingsw.lasout.model.notify.action;

import it.unipv.ingsw.lasout.model.notify.Notify;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EmptyNotifyAction implements INotifyAction{



    @Override
    public void accept() {

    }

    @Override
    public void build(HBox information, HBox box) {

        Label label =  new Label("Notifica di sistema");
        label.getStyleClass().add("additiona-info");
        label.applyCss();
        information.getChildren().add(label);

    }

    @Override
    public void load(Notify notify) throws Exception{


    }

    @Override
    public void delete(Notify notify) throws Exception {

    }

    @Override
    public void save(Notify notify) throws Exception {

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
