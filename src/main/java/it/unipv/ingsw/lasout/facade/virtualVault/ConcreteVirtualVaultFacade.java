package it.unipv.ingsw.lasout.facade.virtualVault;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.model.virtualVault.IVirtualVaultDAO;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ConcreteVirtualVaultFacade implements VirtualVaultFacade {
    //
    private IVirtualVaultDAO virtualVaultDAO;


    public ConcreteVirtualVaultFacade(){
        virtualVaultDAO = DaoFactory.getVirtualVaultDAO();

    }

    private static ConcreteVirtualVaultFacade instance;

    public static ConcreteVirtualVaultFacade getInstance(){
        if(instance==null){
            instance = new ConcreteVirtualVaultFacade();
        }
        return instance;
    }

    @Override
    public boolean newVirtualVault(VirtualVault virtualVault, User user){
        try{
            //Controllo se uno user è loggato
            if(LaVaultFacade.getInstance().getSessionFacade().isLogged()){

                virtualVaultDAO.save(virtualVault);
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    //Serve per restituire un pojo di VirtualVault
    public VirtualVault getVirtualVault(VirtualVault virtualVault){
        VirtualVault result = null;
         try{
            result = virtualVaultDAO.getRaw(virtualVault);
        } catch (Exception e){
            return null;
        }
        return result;
    }
    @Override
    public List<VirtualVault> getAllVirtualVault(User user){
        try{
          return virtualVaultDAO.getAll(user);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    @Override
    //Serve per modificare i virtualVault
    public boolean editVirtualVault(VirtualVault virtualVault){
        try{
            virtualVaultDAO.update(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }
    @Override
    //Serve per eliminare i virtualVault
    public boolean deleteVirtualVault(VirtualVault virtualVault){
        try{
            virtualVaultDAO.delete(virtualVault);
        } catch (Exception e){
            return false;
        }
        return true;
    }




    private static final Logger LOGGER = Logger.getLogger(ConcreteVirtualVaultFacade.class.getName());
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

        //Prova con user
        User user = new User("cla", "miao", "bbb@gmail.com");
        LaVaultFacade.getInstance().getSessionFacade().login(user);
        v.setName("IL PRIMO");
        v.setOwner(new User(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId()));
        v.setBalance(70);
        System.out.println("It's logged in "+LaVaultFacade.getInstance().getSessionFacade().isLogged());
        System.out.println("questo è il mio id "+LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId());

        User user1 = new User("dada", "ciao", "aaa@gmail.com");
        LaVaultFacade.getInstance().getSessionFacade().login(user1);
        v2.setName("IL SECONDO");
        v2.setOwner(new User(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId()));
        v2.setBalance(20);
        System.out.println("It's logged in "+LaVaultFacade.getInstance().getSessionFacade().isLogged());
        System.out.println("questo è il mio id "+LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId());




        //ConcreteVirtualVaultFacade.getInstance().newVirtualVault(v, user);
        ConcreteVirtualVaultFacade.getInstance().newVirtualVault(v2, user1);
        //ConcreteVirtualVaultFacade.getInstance().deleteVirtualVault(v2);


        System.out.println(DaoFactory.getVirtualVaultDAO().getAll(new User(1)));

    }

}
