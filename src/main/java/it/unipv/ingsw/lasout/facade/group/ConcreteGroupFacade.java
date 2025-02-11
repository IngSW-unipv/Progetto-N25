package it.unipv.ingsw.lasout.facade.group;

import io.github.palexdev.materialfx.effects.ripple.base.IRippleGenerator;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.exception.ExecutorNotAdminException;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.group.IGroupDao;
import it.unipv.ingsw.lasout.model.group.spesa.ISpesaDao;
import it.unipv.ingsw.lasout.model.group.spesa.Spesa;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.io.IOException;
import java.io.SyncFailedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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


    }

}
