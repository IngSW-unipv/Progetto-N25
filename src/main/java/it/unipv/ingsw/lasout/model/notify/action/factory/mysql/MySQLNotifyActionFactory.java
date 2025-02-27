package it.unipv.ingsw.lasout.model.notify.action.factory.mysql;

import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.factory.ConcreteNotifyActionFactory;
import it.unipv.ingsw.lasout.model.notify.action.persistence.mysql.MySQLEmptyNotifyActionPersistence;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class MySQLNotifyActionFactory extends ConcreteNotifyActionFactory {

    private final Logger LOGGER = Logger.getLogger(MySQLNotifyActionFactory.class.getName());
    private static final Map<String, INotifyActionPersistence>  persistenceMap = new HashMap<>();

    public MySQLNotifyActionFactory() {
        super();
        Properties properties = new Properties();
        try {
            properties.load(MySQLNotifyActionFactory.class.getResourceAsStream("/mysqlpersistencefactory.properties"));
        } catch (IOException e) {
            LOGGER.severe("Could not load notify factories");
        }
        properties.forEach((key, value) -> {
            try {
                if(persistenceMap.containsKey(key.toString())) { return;}
                persistenceMap.put((String) key, (INotifyActionPersistence) Class.forName(value.toString()).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public INotifyActionPersistence getPersistence(String notify) {
        return persistenceMap.getOrDefault(notify,  new MySQLEmptyNotifyActionPersistence());
    }


}
