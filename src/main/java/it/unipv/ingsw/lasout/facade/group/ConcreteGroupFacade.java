package it.unipv.ingsw.lasout.facade.group;

import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.group.spesa.Spesa;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConcreteGroupFacade implements GroupFacade {

    private ConcreteGroupFacade() {}
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
            GroupDao.getInstance().save(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Group getGroup(Group group) {
        try {
            return GroupDao.getInstance().get(group);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean editGroup(Group group) {
        try {
            GroupDao.getInstance().update(group);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteGroup(Group group) {
        try {
            GroupDao.getInstance().delete(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public boolean removeUserFromGroup(Group group, List<User> userList) {

        Group g;

        try {
            g = getGroup(group);
            for (User u : userList) {
                if (!g.isAdmin(u)){
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
    public boolean leaveGroup(Group group, User user) {

        Group g = getGroup(group);
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
            listaSpese=g.getSpese();
        }catch (Exception e){
            return null;
        }
        return listaSpese;
    }


    private static final Logger LOGGER = Logger.getLogger(ConcreteGroupFacade.class.getName());
    public static void main(String []args) throws Exception {

        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Couldn't initialize database: \n" + e);
            System.exit(1);
            return;
        }

        List<User> userList=new ArrayList<>();

        userList.add(UserDAO.getInstance().get(new User(1)));
        userList.add(UserDAO.getInstance().get(new User(2)));
        userList.add(UserDAO.getInstance().get(new User(3)));

        ConcreteGroupFacade.getInstance().newGroup(new Group(7, "prova", new User(1), userList));

//System.out.println(ConcreteGroupFacade.getInstance().removeUserFromGroup(new Group(7), userList));

        System.out.println(ConcreteGroupFacade.getInstance().leaveGroup(new Group(7), new User(3)));

        System.out.println(ConcreteGroupFacade.getInstance().addUserToGroup(new Group(7), new User(6)));

        //System.out.println(ConcreteGroupFacade.getInstance().deleteGroup(new Group(7)));

        System.out.println(ConcreteGroupFacade.getInstance().addSpesaToGroup(new Group(7), new Spesa(new User(1), new Group(7),600,"prova")));
        System.out.println(ConcreteGroupFacade.getInstance().addSpesaToGroup(new Group(7), new Spesa(new User(2), new Group(7),200,"prova2")));

        System.out.println(ConcreteGroupFacade.getInstance().leaveGroup(new Group(7), new User(2)));
        System.out.println(ConcreteGroupFacade.getInstance().addSpesaToGroup(new Group(7), new Spesa(new User(3), new Group(7),100,"prova2")));

        System.out.println(ConcreteGroupFacade.getInstance().remuveSpesaFromGroup(new Group(7), new Spesa(4)));



//        List<User> userList=new ArrayList<>();
//
//        userList.add(UserDAO.getInstance().get(new User(1)));
//        userList.add(UserDAO.getInstance().get(new User(2)));
//        userList.add(UserDAO.getInstance().get(new User(3)));
//
//        System.out.println(ConcreteGroupFacade.getInstance().removeUserFromGroup(new Group(1), userList));
//        //ConcreteGroupFacade.getInstance().addUserToGroup(new Group(1), new User(3));
//        //System.out.println(ConcreteGroupFacade.getInstance().leaveGroup(new Group(1), new User(3)));



    }

}
