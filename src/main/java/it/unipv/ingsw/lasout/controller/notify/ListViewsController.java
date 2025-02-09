package it.unipv.ingsw.lasout.controller.notify;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.materialfx.utils.ColorUtils;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import io.github.palexdev.virtualizedfx.cell.Cell;
import it.unipv.ingsw.lasout.facade.TempSession;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.NotifyDAO;
import it.unipv.ingsw.lasout.model.user.User;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
