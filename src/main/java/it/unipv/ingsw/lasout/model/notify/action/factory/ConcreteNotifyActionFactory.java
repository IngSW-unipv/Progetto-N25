package it.unipv.ingsw.lasout.model.notify.action.factory;

import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.EmptyNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class ConcreteNotifyActionFactory implements INotifyActionFactory{

    private final Logger LOGGER = Logger.getLogger(ConcreteNotifyActionFactory.class.getName());

    private final Map<String, Class<?>> actionClasses = new HashMap<>();

    public ConcreteNotifyActionFactory() {
        Properties properties = new Properties();
        try {
            properties.load(ConcreteNotifyActionFactory.class.getResourceAsStream("/notifyactionfactory.properties"));
        } catch (IOException e) {
            LOGGER.severe("Could not load notify factories");
        }
        properties.forEach((key, value) -> {
            try {
                actionClasses.put((String) key, Class.forName(value.toString()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    public INotifyAction get(String notify) {
        Class<?> clazz = getActionClass(notify);
        try {
            return (INotifyAction) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<?> getActionClass(String key){
        return actionClasses.getOrDefault(key, EmptyNotifyAction.class);
    }
    /*
    @Override
    public INotifyAction getUnInjected(String notify) {
        Class<?> clazz = getActionClass(notify);
        try {
            return (INotifyAction) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

     */
}
