package it.unipv.ingsw.lasout;


import it.unipv.ingsw.lasout.database.DatabaseUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ProgettoN25  {

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

    }
    
}
