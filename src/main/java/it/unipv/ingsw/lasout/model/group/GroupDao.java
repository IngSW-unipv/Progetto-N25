package it.unipv.ingsw.lasout.model.group;

import it.unipv.ingsw.lasout.dao.DBQuery;
import it.unipv.ingsw.lasout.dao.UserGroupDao;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.util.DatabaseUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IGroupDao {

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

    /* TODO
    inserire eccezioni corrette
    */
    /**
     * Ricerca di un gruppo sul db tramite l'id del gruppo
     * @param fictitiousGroup L'id ricercato
     * @return Oggetto completo del gruppo ricercato
     * @throws Exception non trovato gruppo con l'id inserito
     */
    @Override
    public Group get(Group fictitiousGroup) throws Exception {
        String sql = "SELECT * FROM group WHERE id = ?";
        DBQuery query = DatabaseUtil.getInstance().createQuery(sql, fictitiousGroup.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if(!rs.next()) throw new Exception();
        /* TODO
            aggiungiere restanti attributi
         */
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setAdmin(new User(rs.getInt("admin")));
        group.setMembers(UserGroupDao.getInstance().members(new Group(rs.getInt("id"))));

        query.close();
        return group;
    }

    @Override
    public List<Group> getAll() throws Exception {
        String sql = "SELECT * FROM group";
        DBQuery query = DatabaseUtil.getInstance().createQuery(sql);

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        List<Group> groups = new ArrayList<Group>();
        while(rs.next()){
            Group group = new Group();
            group.setId(rs.getInt("id"));
            group.setAdmin(new User(rs.getInt("admin")));
            group.setMembers(UserGroupDao.getInstance().members(new Group(rs.getInt("id"))));
            groups.add(group);
        }

        query.close();
        return groups;
    }

    @Override
    public void save(Group group) throws Exception {

    }

    @Override
    public void update(Group group, String[] params) throws Exception {

    }

    @Override
    public void delete(Group group) throws Exception {

    }

    public static void main(String []args) throws Exception {
        Group group = GroupDao.getInstance().get(new Group(1));
        System.out.println(group);
    }
}

