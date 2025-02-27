package it.unipv.ingsw.lasout.model.notify.action.mysql;

import it.unipv.ingsw.lasout.model.notify.action.EmptyNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.FriendRequestNotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.friend.mysql.MySQLFriendRequestNotifyActionPersistence;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistenceFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MYSQLNotifyActionPersistenceFactory implements INotifyActionPersistenceFactory {


    private static final Map<String, Class<?>> persistenceLoader = new HashMap<>();


    public  MYSQLNotifyActionPersistenceFactory() {
        persistenceLoader.put(new FriendRequestNotifyAction().type(), MySQLFriendRequestNotifyActionPersistence.class);
        persistenceLoader.put(new EmptyNotifyAction().type(), MySQLEmptyNotifyActionPersistence.class);
    }

    @Override
    public INotifyActionPersistence getPersistence(String key) {
        Class<?>  clazz  = persistenceLoader.get(key);
        if(clazz == null) throw new RuntimeException("Could not find a class for type:" + key);
        try {
            return (INotifyActionPersistence) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
