package it.unipv.ingsw.lasout;

import it.unipv.ingsw.lasout.controller.AppController;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.group.ConcreteGroupFacade;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MainApp {
    private static final Logger LOGGER = Logger.getLogger(ConcreteGroupFacade.class.getName());

    public static void main(String[] args) {

        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.
            getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Couldn't initialize database: \n" + e
            );
            System.exit(1);
            return;
        }

        // Esecuzione su Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            AppController controller = new AppController();
            controller.showLoginView();
        });
    }

}
