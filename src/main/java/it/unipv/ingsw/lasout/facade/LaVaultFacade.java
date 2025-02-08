package it.unipv.ingsw.lasout.facade;

import it.unipv.ingsw.lasout.facade.friend.FriendFacade;
import it.unipv.ingsw.lasout.facade.notify.NotifyFacade;

import java.util.Properties;

public class LaVaultFacade {


    private FriendFacade friendFacade;
    private NotifyFacade notifyFacade;

    private LaVaultFacade() throws Exception {

        this.notifyFacade = (NotifyFacade) loadClass("notify").getDeclaredConstructor().newInstance();
        this.friendFacade = (FriendFacade) loadClass("friend").getDeclaredConstructor().newInstance();
    }

    private Class<?> loadClass(String propertyName) throws Exception {
        Properties properties  = new Properties();
        properties.load(LaVaultFacade.class.getResourceAsStream("/app.properties"));
        return Class.forName(properties.getProperty("facade."+propertyName));
    }


    public FriendFacade getFriendFacade() {
        return friendFacade;
    }

    public NotifyFacade getNotifyFacade() {
        return notifyFacade;
    }
}
