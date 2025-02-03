package it.unipv.ingsw.lasout;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import io.github.palexdev.materialfx.theming.base.Theme;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;

public class ProgettoN25 extends Application {

    public static void main(String[] args) {
        launch();
    }

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.FXVK)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ProgettoN25.class.getResource("/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
