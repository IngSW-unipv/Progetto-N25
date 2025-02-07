package it.unipv.ingsw.lasout.model.group;

import io.github.palexdev.mfxcore.utils.GridUtils;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GroupFacade {

    private GroupFacade() {}
    private static GroupFacade instance;
    public static GroupFacade getInstance() {
        if (instance == null) {
            instance = new GroupFacade();
        }
        return instance;
    }


    /**
     * Crea un nuovo gruppo sul database con le informazioni pasatogli
     * @param group pojo del gruppo da scrivere sul db
     * @return conferma di successo
     */
    public boolean newGroup(Group group) {
        try {
            GroupDao.getInstance().save(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Restituisce un group pojo con i suoi attributi
     * @param group carry con l'id del gruppo ricercato
     * @return conferma di successo
     */
    public boolean getGroup(Group group) {
        try {
            GroupDao.getInstance().get(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * permette di editare i dati di un gruppo sul db
     * @param group pojo del con le informazioni del gurppo che si vuole modificare l'id indica i dati che andranno riscritti
     * @return conferma di successo
     */
    public boolean editGroup(Group group) {
        try {
            GroupDao.getInstance().update(group);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Permette di eliminare un gruppo dal db
     * @param group carry contentente l'id del gruppo da eliminare
     * @return conferma di successo
     */
    public boolean deleteGroup(Group group) {
        try {
            GroupDao.getInstance().delete(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina una lista di utenti da un gruppo
     * @param group carry con l'id del gruppo da cui si vogliono eliminare gli user
     * @param userList Lista di Carry con l'id delle persone che si vogliono eliminare
     * @return conferma di successo
     */
    public boolean removeUserFromGroup(Group group, List<User> userList)  {

        Group g;

        try {
            g=GroupDao.getInstance().get(group);
            for (User u:userList) {
                g.delateMember(u);
            }
            GroupDao.getInstance().update(g);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Aggiungi un user al gruppo
     * @param group carry con l'id del gruppo da cui si vuole aggiungere gli user
     * @param user
     * @return
     */
    public boolean addUserToGroup(Group group, User user){

        Group g;

        try {
            g=GroupDao.getInstance().get(group);
            g.addMember(user);
            GroupDao.getInstance().update(g);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private static final Logger LOGGER = Logger.getLogger(GroupFacade.class.getName());
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

        GroupFacade.getInstance().removeUserFromGroup(new Group(1), userList);
        GroupFacade.getInstance().addUserToGroup(new Group(1), new User(3));

    }

}
