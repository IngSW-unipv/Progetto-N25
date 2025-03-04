package it.unipv.ingsw.lasout.util;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.cashbook.ICashbookDAO;
import it.unipv.ingsw.lasout.model.group.IGroupDao;
import it.unipv.ingsw.lasout.model.group.spesa.ISpesaDao;
import it.unipv.ingsw.lasout.model.notify.dao.INotifyDAO;
import it.unipv.ingsw.lasout.model.transaction.ITransactionDAO;
import it.unipv.ingsw.lasout.model.user.IUserDAO;
import it.unipv.ingsw.lasout.model.vault.IVaultDAO;
import it.unipv.ingsw.lasout.model.virtualVault.IVirtualVaultDAO;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVaultDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DaoFactory {

    private static final Map<Class<?>, IDao<?>> lodadedDao = new HashMap<>();


    public static INotifyDAO getNotifyDAO() {
        return get(INotifyDAO.class);
    }

    public static ISpesaDao getSpesaDAO() {
        return get(ISpesaDao.class);
    }

    public static IGroupDao getGroupDAO() {
        return get(IGroupDao.class);
    }

    public static IVaultDAO getVaultDAO() {
        return get(IVaultDAO.class);
    }

    public static IUserDAO getUserDAO() {
        return get(IUserDAO.class);
    }

    public static VirtualVaultDAO getVirtualVaultDAO() {return (VirtualVaultDAO) get(IVirtualVaultDAO.class);}

    public static ICashbookDAO getCashbookDAO() {
        return get(ICashbookDAO.class);
    }

    public static ITransactionDAO getTransactionDAO() {return get(ITransactionDAO.class);}

    private static <T> T get(Class<T> clazz){
        if (lodadedDao.containsKey(clazz)) {
            Object x=  lodadedDao.get(clazz);
            return clazz.cast(x);
        }

        Properties properties = new Properties();
        try {
            properties.load(DaoFactory.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Class<?> retClass;
        try {
            System.out.println(properties.getProperty("dao."+ clazz.getSimpleName().toLowerCase()));
            retClass = Class.forName(properties.getProperty("dao."+ clazz.getSimpleName().toLowerCase()));
            Object x = retClass.getDeclaredConstructor().newInstance();
            T val = clazz.cast(x);
            lodadedDao.put(clazz,  (IDao<?>) x);
            return val;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
