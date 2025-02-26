package it.unipv.ingsw.lasout.model.notify.action.persistence;

import it.unipv.ingsw.lasout.model.notify.MySQLNotifyDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class DefaultNotifyActionPersistenceFactory implements INotifyActionPersistenceFactory{

    private static final Logger LOGGER = Logger.getLogger(DefaultNotifyActionPersistenceFactory.class.getName());

    private final Map<String, Class<?>> persistenceLoader;


    public DefaultNotifyActionPersistenceFactory() {
        persistenceLoader  = new HashMap<>();

        Properties properties = new Properties();
        try {
            properties.load(MySQLNotifyDAO.class.getResourceAsStream("/notifyperistence.properties"));
        } catch (IOException e) {
            LOGGER.severe("Could not load notify action persistence");
        }
        properties.forEach((key, value) -> {
            try {
                persistenceLoader.put((String) key, Class.forName(value.toString()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }



    @Override
    public INotifyActionPersistence getPersistence(String key) {
        return null;
    }
}
