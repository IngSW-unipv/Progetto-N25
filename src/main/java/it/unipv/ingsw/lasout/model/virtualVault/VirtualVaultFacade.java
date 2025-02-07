package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VirtualVaultFacade {


    private VirtualVaultFacade(){}
    private static VirtualVaultFacade instance;
    public static VirtualVaultFacade getInstance(){
        if(instance==null){
            instance = new VirtualVaultFacade();
        }
        return instance;
    }


    //Creazione nuovo VirtualVault
    public boolean newVirtualVault(VirtualVault virtualVault){
        try{
            VirtualVaultDAO.getInstance().save(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    //Serve per restituire un pojo di VirtualVault
    public boolean getVirtualVault(VirtualVault virtualVault){
        try{
            VirtualVaultDAO.getInstance().get(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    //Serve per editare i virtualVault
    public boolean editVirtualVault(VirtualVault virtualVault){
        try{
            VirtualVaultDAO.getInstance().update(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    //Serve per eliminare i virtualVault
    public boolean deleteVirtualVault(VirtualVault virtualVault){
        try{
            VirtualVaultDAO.getInstance().delete(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }


    private static final Logger LOGGER = Logger.getLogger(VirtualVaultFacade.class.getName());
    public static void main(String []args) throws Exception {

        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Couldn't initialize database: \n" + e);
            System.exit(1);
            return;
        }

        VirtualVault v = new VirtualVault();;
        VirtualVault v2 = new VirtualVault();;

        v.setOwner(new User(3));
        v.setID(3);
        v.setBalance(2);
        v2.setOwner(new User(5));
        v2.setID(5);
        v2.setBalance(2000);

        VirtualVaultDAO.getInstance().save(v);
        VirtualVaultDAO.getInstance().save(v2);
        //VirtualVaultDAO.getInstance().delete(v2);


    }

}
