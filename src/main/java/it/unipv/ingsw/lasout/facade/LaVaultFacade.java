package it.unipv.ingsw.lasout.facade;

import it.unipv.ingsw.lasout.facade.cashbook.CashbookFacade;
import it.unipv.ingsw.lasout.facade.cashbook.ICashbookFacade;
import it.unipv.ingsw.lasout.facade.friend.FriendFacade;
import it.unipv.ingsw.lasout.facade.group.GroupFacade;
import it.unipv.ingsw.lasout.facade.notify.NotifyFacade;
import it.unipv.ingsw.lasout.facade.transaction.ITransactionFacade;
import it.unipv.ingsw.lasout.facade.transaction.TransactionFacade;
import it.unipv.ingsw.lasout.facade.user.ISessionFacade;
import it.unipv.ingsw.lasout.facade.user.IUserFacade;
import it.unipv.ingsw.lasout.facade.vault.VaultFacade;
import it.unipv.ingsw.lasout.facade.virtualVault.VirtualVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private VaultFacade vaultFacade;
    private VirtualVaultFacade virtualVaultFacade;
    private ICashbookFacade cashbookFacade;
    private ITransactionFacade transactionFacade;

    private LaVaultFacade() {

        try{
            this.userFacade = (IUserFacade) loadClass("user").getDeclaredConstructor().newInstance();
            this.notifyFacade = (NotifyFacade) loadClass("notify").getDeclaredConstructor().newInstance();
            this.friendFacade = (FriendFacade) loadClass("friend").getDeclaredConstructor().newInstance();
            this.sessionFacade = (ISessionFacade) loadClass("session").getDeclaredConstructor().newInstance();
            this.groupFacade = (GroupFacade) loadClass("group").getDeclaredConstructor().newInstance();
            this.vaultFacade = (VaultFacade) loadClass("vault").getDeclaredConstructor().newInstance();
            this.virtualVaultFacade = (VirtualVaultFacade) loadClass("virtualvault").getDeclaredConstructor().newInstance();
            System.out.println(virtualVaultFacade+"\n\n\n\n palle");
            this.cashbookFacade = (ICashbookFacade) loadClass("cashbook").getDeclaredConstructor().newInstance();
            this.transactionFacade = (ITransactionFacade) loadClass("transaction").getDeclaredConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Class<?> loadClass(String propertyName) throws Exception {
        Properties properties  = new Properties();
        properties.load(LaVaultFacade.class.getResourceAsStream("/app.properties"));
        return Class.forName(properties.getProperty("facade."+propertyName));
    }


    public VirtualVaultFacade getVirtualVaultFacade() {return virtualVaultFacade;}

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
    
    public VaultFacade getVaultFacade() {
    	return vaultFacade;
    }
    
    public ICashbookFacade getCashbookFacade() {
        return cashbookFacade;
    }

    public ITransactionFacade getTransactionFacade() {
        return transactionFacade;
    }
}
