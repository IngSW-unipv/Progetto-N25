package it.unipv.ingsw.lasout.facade;

import it.unipv.ingsw.lasout.facade.friend.FriendFacade;
import it.unipv.ingsw.lasout.facade.group.GroupFacade;
import it.unipv.ingsw.lasout.facade.notify.NotifyFacade;
import it.unipv.ingsw.lasout.facade.user.ISessionFacade;
import it.unipv.ingsw.lasout.facade.user.IUserFacade;

import java.io.IOException;
import java.util.Properties;

public class LaVaultFacade {

    private static final LaVaultFacade INSTANCE = new LaVaultFacade();
    public static LaVaultFacade getInstance() {
        return INSTANCE;
    }

    private IUserFacade userFacade;
    private ISessionFacade sessionFacade;
    private FriendFacade friendFacade;
    private NotifyFacade notifyFacade;
    private GroupFacade groupFacade;

    private LaVaultFacade() {

        try{
            this.notifyFacade = (NotifyFacade) loadClass("notify").getDeclaredConstructor().newInstance();
            this.friendFacade = (FriendFacade) loadClass("friend").getDeclaredConstructor().newInstance();
            this.sessionFacade = (ISessionFacade) loadClass("session").getDeclaredConstructor().newInstance();
            this.userFacade = (IUserFacade) loadClass("user").getDeclaredConstructor().newInstance();
            this.groupFacade = (GroupFacade) loadClass("group").getDeclaredConstructor().newInstance();
        }catch (Exception e){

        }

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

    public ISessionFacade getSessionFacade() {
        return sessionFacade;
    }

    public IUserFacade getUserFacade() {
        return userFacade;
    }

    public GroupFacade getGroupFacade() { return groupFacade; }
}
