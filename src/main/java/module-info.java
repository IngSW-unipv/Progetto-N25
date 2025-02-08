module it.unipv.ingsw.lasout {
    requires MaterialFX;
    requires VirtualizedFX;
    requires fr.brouillard.oss.cssfx;

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.xml.crypto;

    exports it.unipv.ingsw.lasout;
    exports it.unipv.ingsw.lasout.controller;
    exports it.unipv.ingsw.lasout.model.notify;

    opens it.unipv.ingsw.lasout.controller to javafx.fxml;
    exports it.unipv.ingsw.lasout.controller.notify;
    opens it.unipv.ingsw.lasout.controller.notify to javafx.fxml;
}