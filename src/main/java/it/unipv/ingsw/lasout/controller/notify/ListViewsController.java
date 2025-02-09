package it.unipv.ingsw.lasout.controller.notify;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.NotifyDAO;
import it.unipv.ingsw.lasout.model.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ListViewsController implements Initializable {

    @FXML
    private MFXListView<Notify> notifiesList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Notify> notifies = FXCollections.observableArrayList();


        try {
            notifies.addAll(NotifyDAO.getInstance().notifiesOf(new User(1)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        StringConverter<Notify> converter = FunctionalStringConverter.to(person -> "");



        notifiesList.setItems(notifies);
        notifiesList.setConverter(converter);
        notifiesList.setCellFactory(person -> new NotifyCellFactory(notifiesList, person));
        notifiesList.features().enableBounceEffect();
        notifiesList.features().enableSmoothScrolling(0.5);
    }


}
