package it.unipv.ingsw.lasout.facade;

import it.unipv.ingsw.lasout.facade.friend.FriendFacade;
import it.unipv.ingsw.lasout.facade.notify.NotifyFacade;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class LaVaultFacade {


    private FriendFacade friendFacade;
    private NotifyFacade notifyFacade;


    private LaVaultFacade() throws Exception {

        Properties properties  = new Properties();

        properties.load(LaVaultFacade.class.getResourceAsStream("/app.properties"));

        Class<?> clazz  = Class.forName(properties.getProperty("facade.notify"));
        this.notifyFacade = (NotifyFacade) clazz.getDeclaredConstructor().newInstance();

        clazz  = Class.forName(properties.getProperty("facade.friend"));
        this.friendFacade = (FriendFacade) clazz.getDeclaredConstructor().newInstance();



    }

    public FriendFacade getFriendFacade() {
        return friendFacade;
    }

    public NotifyFacade getNotifyFacade() {
        return notifyFacade;
    }
}
