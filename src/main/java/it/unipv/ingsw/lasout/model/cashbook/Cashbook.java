package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.ProgettoN25;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.user.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Cashbook {
    private int id;
    private String name;
    private LinkedList<Movement> transactionList;


    public Cashbook(){
    }

    public Cashbook(String name, LinkedList<Movement> transactionList){
        this.name = name;
        this.transactionList=transactionList;
    }

    private static final Logger LOGGER = Logger.getLogger(Cashbook.class.getName());
    public static void main(String []args) throws Exception {

        try {
            DatabaseUtil.getInstance().prepare();
            DatabaseUtil.getInstance().initialize();
        } catch (IOException | SQLException e) {
            LOGGER.severe("Couldn't initialize database: \n" + e);
            System.exit(1);
            return;
        }

        Group group = GroupDao.getInstance().get(new Group(2));
        System.out.println(group);

        List<User> users = new ArrayList<>();
        users.add(new User(1));
        users.add(new User(2));
        users.add(new User(3));

        GroupDao.getInstance().save(new Group("VacanzaChieti", new User(2),users));
        //GroupDao.getInstance().delete(new Group(1));
        //GroupDao.getInstance().save(new Group(1,"VacanzaChieti", new User(2)));
        //GroupDao.getInstance().delete(new Group(1));
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTransactionList(){
        return name;
    }

    public void setTransactionList(LinkedList<Movement> transactionList){
        this.transactionList=transactionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
