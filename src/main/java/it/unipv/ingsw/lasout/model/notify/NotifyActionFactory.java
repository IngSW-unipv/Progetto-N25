package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.model.notify.action.EmptyNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class NotifyActionFactory {

    private static final Logger LOGGER = Logger.getLogger(NotifyActionFactory.class.getName());

    private static final Map<String, Class<?>> classes = new  HashMap<>();

    static {

        Properties properties = new Properties();
        try {
            properties.load(MySQLNotifyDAO.class.getResourceAsStream("/factories.properties"));
        } catch (IOException e) {
            LOGGER.severe("Could not load notify factories");
        }
        properties.forEach((key, value) -> {
            try {
                classes.put((String) key, Class.forName(value.toString()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static Class<?> getFactory(String key){
        return classes.getOrDefault(key, EmptyNotifyAction.class);
    }

    public static INotifyAction get(String key){

        Class<?> clazz = getFactory(key);
        try {
            return (INotifyAction) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }
}
