package it.unipv.ingsw.lasout.facade.group;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.exception.ExecutorNotAdminException;
import it.unipv.ingsw.lasout.model.group.Debito;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.IGroupDao;
import it.unipv.ingsw.lasout.model.group.spesa.Spesa;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConcreteGroupFacade implements GroupFacade {

    private IGroupDao groupDao;

    public ConcreteGroupFacade() {
        groupDao= DaoFactory.getGroupDAO();
    }


    private static ConcreteGroupFacade instance;

    public static ConcreteGroupFacade getInstance() {
        if (instance == null) {
            instance = new ConcreteGroupFacade();
        }
        return instance;
    }


    @Override
    public boolean newGroup(Group group) {
        try {
            groupDao.save(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Group getGroup(Group group) {
        try {
            return groupDao.get(group);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean editGroup(Group group) {
        try {
            groupDao.update(group);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteGroup(Group group) {
        try {
            groupDao.delete(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<User> getUsersFromGroup(Group group) {
        List<User> listauser = null;
        try {
            listauser= groupDao.get(group).getMembers();
        }catch (Exception e) {
            return null;
        }
        return listauser;
    }


    @Override
    public boolean removeUserFromGroup(Group group, List<User> userList) {

        if (!group.isAdmin(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser())) return false;
        Group g;

        try {
            if (!group.isAdmin(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser()))
                throw new ExecutorNotAdminException("removeUserFromGroup list must be admin");
            g = getGroup(group);
            for (User u : userList) {
                if (!g.isAdmin(u)) {
                    g.deleteMember(u);
                }
            }
            editGroup(g);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public boolean removeUserFromGroup(Group group, User user) {
        Group g;
        try {
            if (!group.isAdmin(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser()))
                throw new ExecutorNotAdminException("removeUserFromGroup must be admin");
            g = getGroup(group);
            if (!g.isAdmin(user)) {
                g.deleteMember(user);
            } else return false;
            editGroup(g);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addUserToGroup(Group group, User user) {
        Group g;
        try {
            g = getGroup(group);
            g.addMember(user);
            editGroup(g);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean invite(Group group, User user) {
        if (!group.isAdmin(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser()))
            throw new ExecutorNotAdminException("addUserToGroup must be admin");
        return LaVaultFacade.getInstance().getNotifyFacade().sendInviteGroupRequest(group, user);
    }

    @Override
    public boolean leaveGroup(Group group, User user) {
        Group g = getGroup(group);
        if (g == null) return false;
        if (g.isAdmin(user)) return false;
        return removeUserFromGroup(g, user);
    }

    @Override
    public boolean leaveGroup(Group group) {
        Group g = getGroup(group);
        if (g == null) return false;
        User user = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
        if (g.isAdmin(user)) return false;
        return removeUserFromGroup(g, user);
    }

    @Override
    public boolean addSpesaToGroup(Group group, Spesa spesa) {
        try {
            Group g = getGroup(group);
            g.addSpesa(spesa);
            editGroup(g);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean remuveSpesaFromGroup(Group group, Spesa spesa) {
        try {
            Group g = getGroup(group);
            g.deleteSpesa(spesa);
            editGroup(g);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Spesa> getSpeseFromGroup(Group group) {
        List<Spesa> listaSpese = new ArrayList<Spesa>();
        try {
            Group g = getGroup(group);
            listaSpese = g.getSpese();
        } catch (Exception e) {
            return null;
        }
        return listaSpese;
    }

    @Override
    public List<Debito> getDebitFromGroupByUser(Group group, User user) {

        List<Debito> listaDebiti = new ArrayList<>();
        Group g = getGroup(group);
        List<Spesa> listaSpesa = g.getSpese();
        List<User> membri = g.getMembers();

        for(User u : membri) {
            if(!u.equals(user)) listaDebiti.add(new Debito(user, u, 0));
        }

        for(Spesa spesa : listaSpesa) {
            if(user.equals(spesa.getEsecutore())){
               double div= spesa.getAmount()/ membri.size();
               for(Debito d : listaDebiti) {
                   d.addDebito(div);
               }
            }
        }
        return listaDebiti;
    }

    @Override
    public List<Debito> getDebitFromGroupByLogedUser(Group group){
        return  getDebitFromGroupByUser(group,LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
    }

    @Override
    public List<Debito> getDebitiFromGroup(Group group) {
        List<Debito> listaDebiti = new ArrayList<>();
        Group g = getGroup(group);
        List<User> membri = g.getMembers();

        for(User u : membri) {
            listaDebiti.addAll(getDebitFromGroupByUser(group, u));
        }
        return listaDebiti;
    }

    @Override
    public boolean finalizzaDebiti(Group group) {
        Group g = getGroup(group);
        if (!g.isAdmin(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser())) return false;
        List<Debito> listaDebiti = getDebitiFromGroup(g);
        for (Debito d : listaDebiti) {
            LaVaultFacade.getInstance().getNotifyFacade().sendPayRequestByUser(d.getCreditore(),d.getDebitore(),d.getDebito());
        }
        return true;
    }






    private static final Logger LOGGER = Logger.getLogger(ConcreteGroupFacade.class.getName());

    public static void main(String[] args) throws Exception {

        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Couldn't initialize database: \n" + e);
            System.exit(1);
            return;
        }

        List<User> userList = new ArrayList<>();

        userList.add(UserDAO.getInstance().get(new User(1)));
        userList.add(UserDAO.getInstance().get(new User(2)));
        userList.add(UserDAO.getInstance().get(new User(3)));

        ConcreteGroupFacade.getInstance().newGroup(new Group(7, "prova", new User(1), userList));

//System.out.println(ConcreteGroupFacade.getInstance().removeUserFromGroup(new Group(7), userList));

        System.out.println(ConcreteGroupFacade.getInstance().leaveGroup(new Group(7), new User(3)));

        System.out.println(ConcreteGroupFacade.getInstance().addUserToGroup(new Group(7), new User(6)));

        //System.out.println(ConcreteGroupFacade.getInstance().deleteGroup(new Group(7)));

        System.out.println(ConcreteGroupFacade.getInstance().addSpesaToGroup(new Group(7), new Spesa(new User(1), new Group(7), 600, "prova")));
        System.out.println(ConcreteGroupFacade.getInstance().addSpesaToGroup(new Group(7), new Spesa(new User(2), new Group(7), 200, "prova2")));

        System.out.println(ConcreteGroupFacade.getInstance().leaveGroup(new Group(7), new User(2)));
        System.out.println(ConcreteGroupFacade.getInstance().addSpesaToGroup(new Group(7), new Spesa(new User(3), new Group(7), 100, "prova2")));

        System.out.println(ConcreteGroupFacade.getInstance().remuveSpesaFromGroup(new Group(7), new Spesa(4)));


        List<Debito> d=ConcreteGroupFacade.getInstance().getDebitiFromGroup(new Group(1));
        for (int i=0; i<d.size(); i++){
          System.out.println(d.get(i).getDebitore().getId()+" deve  "+d.get(i).getDebito() +" a "+ d.get(i).getCreditore().getId());
        }

        LaVaultFacade.getInstance().getSessionFacade().login(new User("cla", "miao", "bbb@gmail.com"));
        System.out.println("It's logged in "+LaVaultFacade.getInstance().getSessionFacade().isLogged());
        System.out.println(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());

    }

}
