module it.unipv.ingsw.lasout {
    requires java.sql;
    requires java.xml.crypto;
    requires mysql.connector.j;
	requires java.desktop;

    exports it.unipv.ingsw.lasout;
    exports it.unipv.ingsw.lasout.controller;
    exports it.unipv.ingsw.lasout.model.notify;


}