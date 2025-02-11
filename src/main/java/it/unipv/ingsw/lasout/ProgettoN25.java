package it.unipv.ingsw.lasout;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ProgettoN25 extends Application {

    private static final Logger LOGGER = Logger.getLogger(ProgettoN25.class.getName());

    public static void main(String[] args) throws Exception {


        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Couldn't initialize database: \n" + e);
            System.exit(1);
            return;
        }

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

        scene = new Scene(loadFXML("it/unipv/ingsw/lasout/view/home/home"));
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
