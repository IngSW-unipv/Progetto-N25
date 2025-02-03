package it.unipv.ingsw.lasout.controller;

import java.io.IOException;

import it.unipv.ingsw.lasout.ProgettoN25;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        ProgettoN25.setRoot("secondary");
    }
}