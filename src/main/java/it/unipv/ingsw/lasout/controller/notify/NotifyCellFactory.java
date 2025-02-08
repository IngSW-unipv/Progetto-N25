package it.unipv.ingsw.lasout.controller.notify;

import fr.brouillard.oss.cssfx.CSSFX;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.effects.DepthLevel;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import io.github.palexdev.mfxresources.fonts.fontawesome.FontAwesomeBrands;
import io.github.palexdev.mfxresources.fonts.fontawesome.FontAwesomeRegular;
import io.github.palexdev.mfxresources.fonts.fontawesome.FontAwesomeSolid;
import it.unipv.ingsw.lasout.model.notify.Notify;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;

public class NotifyCellFactory extends MFXListCell<Notify> {

    @FXML
    private HBox hBox;

    @FXML
    private HBox information;

    @FXML
    private Label  text;

    @FXML
    private MFXButton btn;

    @FXML
    private MFXFontIcon dlt;




    private Parent node;

    private Notify data;

    public NotifyCellFactory(MFXListView<Notify> listView, Notify data)   {
        super(listView, data);
        this.data = data;

        FXMLLoader fxmlLoader = new FXMLLoader(NotifyCellFactory.class.getResource("/it/unipv/ingsw/lasout/view/notify/notifycell.fxml"));
        try {
            fxmlLoader.setController(this);
            node = fxmlLoader.load();

            dlt.setColor(Color.rgb(0,0,0,0.3));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        render(data);
    }

    @Override
    protected void render(Notify data) {
        super.render(data);


        if(node == null) return;
/*
        VBox container = new VBox();
        container.setSpacing(8);
        container.setPadding(new Insets(8));
        container.setMaxWidth(Double.MAX_VALUE);

        Label content = new Label(data.getDescription());
        content.setWrapText(true);
        content.setMaxWidth(Double.MAX_VALUE);
        content.setStyle("-fx-font-size: 14;");

        MFXButton button = new MFXButton("Action");

        container.getChildren().addAll(content, button);

        StackPane wrapper = new StackPane(container);
        wrapper.setAlignment(Pos.TOP_LEFT);
        wrapper.setPadding(new Insets(5));

        getChildren().add(wrapper);

 */

        dlt.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), dlt);
            st.setFromX(1.0);
            st.setFromY(1.0);
            st.setToX(1.2);  // Zoom del 20%
            st.setToY(1.2);
            st.setAutoReverse(true);
            st.play();

            // Opzionale: cambia colore durante lo zoom
            dlt.setColor(Color.rgb(245, 108, 108));
        });

        dlt.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), dlt);
            st.setFromX(1.2);
            st.setFromY(1.2);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();

            dlt.setColor(Color.rgb(0,0,0,0.3));
        });

        StackPane pane = (StackPane) node;
        pane.prefWidthProperty().bind(this.widthProperty());


        text.setText(data.getDescription());
        getChildren().add(node);



        setMaxWidth(Double.MAX_VALUE);
        setPrefHeight(150);
        setMinHeight(USE_COMPUTED_SIZE);


        data.getNotifyAction().build(information, hBox);
    }

    @FXML
    public void delete(){
        if(data == null) return;
        System.out.println(data.getDescription());
    }
}