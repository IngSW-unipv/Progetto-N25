package it.unipv.ingsw.lasout.model.group;


import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.user.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GroupDao implements IGroupDao{

    /**
     * Istanza singola del GroupDao (implementazione singleton)
     */
    private static GroupDao instance = null;

    /**
     *
     * @return l'istanza singleton del GroupDao
     */
    public static GroupDao getInstance(){
        if (instance == null){
            instance= new GroupDao();
        }
        return instance;
    }

    /**
     * Rendo il costruttore privato
     */
    private GroupDao(){
        super();
    }

    private static final String GET_GROUP_FROM_ID ="SELECT * FROM £group£ WHERE id = ?";
    private static final String GAT_ALL_GROUP = "SELECT * FROM £group£";
    private static final String INSERT_GROUP_NOID="INSERT INTO £group£ (name, user_id) VALUES(?,?)";
    private static final String INSERT_GROUP_ID="INSERT INTO £group£ (id, name, user_id) VALUES(?,?,?)";
    private static final String  DELATE_GROUP_FROM_ID="DELETE FROM £group£ WHERE id = ?";
    private static final String GET_USER_FROM_USERGROUP ="SELECT * FROM £usergroup£ WHERE group_id = ?";
    private static final String DELATE_ASSO_FROM_USERGROUP="DELETE FROM £usergroup£ WHERE group_id = ?";
    private static final String INSERT_ASSO_FROM_USERGROUP="INSERT INTO £usergroup£ (user_id, group_id) VALUES(?,?)";

    /**
     * Ricerca di un gruppo sul db tramite l'id del gruppo
     * @param fictitiousGroup L'id ricercato
     * @return Oggetto completo del gruppo ricercato
     * @throws Exception non trovato gruppo con l'id inserito
     */
    @Override
    public Group get(Group fictitiousGroup) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_GROUP_FROM_ID, fictitiousGroup.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if(!rs.next()) throw new Exception();

        //creazione del pojo
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        group.setAdmin(new User(rs.getInt("user_id")));
        group.setMembers(members(new Group(rs.getInt("id"))));

        query.close();
        return group;
    }

    /**
     *
     * @return Lista di tutti i gruppi presenti nel database
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    @Override
    public List<Group> getAll() throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GAT_ALL_GROUP);

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        List<Group> groups = new ArrayList<Group>();
        while(rs.next()){
            Group group = new Group();
            group.setId(rs.getInt("id"));
            group.setName(rs.getString("name"));
            group.setAdmin(new User(rs.getInt("admin")));
            group.setMembers(members(new Group(rs.getInt("id"))));
            groups.add(group);
        }

        query.close();
        return groups;
    }

    /**
     *
     * @param fictitiousGroup ogetto contenente il solo identificatore dell'entità
     * @return un pojo group contenente solo le informazioni raw
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    public Group getRaw(Group fictitiousGroup) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_GROUP_FROM_ID, fictitiousGroup.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if(!rs.next()) throw new Exception();
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        group.setAdmin(new User(rs.getInt("user_id")));

        query.close();
        return group;
    }


    @Override
    public void save(Group group) throws Exception {

        DBQuery query;
        if(group.getId()!=0){
            query = DatabaseUtil.getInstance().createQuery(INSERT_GROUP_ID, group.getId(), group.getName(), group.getAdmin().getId());
        }else{
            query = DatabaseUtil.getInstance().createQuery(INSERT_GROUP_NOID, group.getName(), group.getAdmin().getId());
        }
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();
        if(rs!=null)throw new Exception();

        group.setId(query.getPreparedStatement().getGeneratedKeys().getLong(1));
        saveAssociation(group);

        // chass
        query.close();
    }

    @Override
    public void update(Group group, String[] params) throws Exception {

    }

    @Override
    public void delete(Group group) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELATE_GROUP_FROM_ID, group.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if(rs!=null)throw new Exception();

        deleteAssociation(group);
        query.close();
    }

    //altro dao usergroupDao
    public List<User> members(Group group) throws Exception{
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_USER_FROM_USERGROUP, group.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        //lista di pojo
        List<User> user = new ArrayList<User>();
        while(rs.next()){
            //ogni volta un nuovo pojo
            User u = new User(rs.getInt("user_id"));
            user.add(u);
        }
        query.close();
        return user;
    }

    public void deleteAssociation(Group group) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELATE_ASSO_FROM_USERGROUP, group.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if(rs!=null)throw new Exception();
        query.close();
    }

    public void saveAssociation(Group group) throws Exception {
        DBQuery query = null;
        for(User u : group.getMembers()){
            query = DatabaseUtil.getInstance().createQuery(INSERT_ASSO_FROM_USERGROUP,u.getId(),group.getId());
            DatabaseUtil.getInstance().executeQuery(query);
            ResultSet rs = query.getResultSet();

            if(rs!=null)throw new Exception();
        }
        if(query!=null) query.close();
    }

    private static final Logger LOGGER = Logger.getLogger(GroupDao.class.getName());
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
        GroupDao.getInstance().delete(new Group(1));
    }


}

