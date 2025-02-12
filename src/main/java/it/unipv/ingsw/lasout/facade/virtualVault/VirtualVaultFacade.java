package it.unipv.ingsw.lasout.facade.virtualVault;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVaultDAO;
import it.unipv.ingsw.lasout.model.virtualVault.IVirtualVaultDAO;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class VirtualVaultFacade implements IVirtualVaultFacade {
    //
    private IVirtualVaultDAO virtualVaultDAO;



    public VirtualVaultFacade(){
        virtualVaultDAO = DaoFactory.getVirtualVaultDAO();

    }

    private static VirtualVaultFacade instance;

    public static VirtualVaultFacade getInstance(){
        if(instance==null){
            instance = new VirtualVaultFacade();
        }
        return instance;
    }

    @Override
    public boolean newVirtualVault(VirtualVault virtualVault, User user){

        try{
            //Controllo se uno user è loggato
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){

                VirtualVaultDAO.getInstance().save(virtualVault);
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    //Serve per restituire un pojo di VirtualVault
    public boolean getVirtualVault(VirtualVault virtualVault){
        try{
            VirtualVaultDAO.getInstance().get(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    @Override
    //Serve per modificare i virtualVault
    public boolean editVirtualVault(VirtualVault virtualVault){
        try{
            VirtualVaultDAO.getInstance().update(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    @Override
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
        //VirtualVault v2 = new VirtualVault();;
        //Prova con user
        User user = new User("cla", "miao", "bbb@gmail.com");
        LaVaultFacade.getInstance().getSessionFacade().login(user);
        System.out.println("It's logged in "+LaVaultFacade.getInstance().getSessionFacade().isLogged());
        //System.out.println("questo è il mio id "+LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId());

        v.setName("IL PRIMO");
        v.setOwner(new User(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId()));
        v.setBalance(7000);
//       v2.setOwner(new User(5));
//       v2.setID(5);
//       v2.setBalance(2000);



        //VirtualVaultDAO.getInstance().save(v);
        //VirtualVaultDAO.getInstance().save(v2);
        //VirtualVaultDAO.getInstance().delete(v2);

        VirtualVaultFacade.getInstance().newVirtualVault(v, user);


    }

}
