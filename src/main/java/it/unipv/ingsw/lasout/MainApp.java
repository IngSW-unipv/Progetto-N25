package it.unipv.ingsw.lasout;

import it.unipv.ingsw.lasout.controller.AppController;
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        // Esecuzione su Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            AppController controller = new AppController();
            controller.showLoginView();
        });
    }
}
