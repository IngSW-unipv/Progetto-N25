package it.unipv.ingsw.lasout.controller.home;

import io.github.palexdev.materialfx.controls.MFXButton;
import it.unipv.ingsw.lasout.ProgettoN25;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane  display;
    @FXML
    private VBox btnContainer;

    private Node errorPage;

    private Map<String, Node> cached = new HashMap<>();

    private NavigationButton active;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader errorLoader = new FXMLLoader(ProgettoN25.class.getResource("/it/unipv/ingsw/lasout/view/home/error.fxml"));
        try {
            errorPage = errorLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnContainer.getChildren().add(new NavigationButton(this, "Home", null));
        btnContainer.getChildren().add(new NavigationButton(this,"Vault", null));
        btnContainer.getChildren().add(new NavigationButton(this,"Notifies", "notify/notifylist"));
        btnContainer.getChildren().add(new NavigationButton(this,"Group", null));
        btnContainer.getChildren().add(new NavigationButton(this,"Settings", null));

    }


    public void  changeActive(NavigationButton  navigationButton){
        if(active != null){
            active.getStyleClass().remove("btn-active");
        }
        navigationButton.getStyleClass().add("btn-active");
        this.active = navigationButton;
    }

    public void setDisplay(String nodeName)  {

        display.getChildren().clear();
        if(cached.containsKey(nodeName)) {
            display.getChildren().add(cached.get(nodeName));
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(ProgettoN25.class.getResource("/it/unipv/ingsw/lasout/view/"+nodeName+".fxml"));
        Node node = null;
        try {
            node = fxmlLoader.load();
        } catch (Exception e) {
            node = errorPage;
        }
        AnchorPane.setTopAnchor(node, 0d);
        AnchorPane.setRightAnchor(node, 0d);
        AnchorPane.setLeftAnchor(node, 0d);
        AnchorPane.setBottomAnchor(node, 0d);
        display.getChildren().add(node);
        cached.put(nodeName, node);
    }


}
