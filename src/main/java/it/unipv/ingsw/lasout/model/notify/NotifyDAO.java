package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DatabaseUtil;

import java.sql.ResultSet;
import java.util.List;

public class NotifyDAO implements IDao<Notify> {

    private static final NotifyDAO INSTANCE = new NotifyDAO();
    public static NotifyDAO getInstance() {
        return INSTANCE;
    }
    private NotifyDAO() {

    }

    private static final String QUERY_NOTIFY_1 =
            "SELECT * " +
            "FROM `notify` " +
            "WHERE `id` = ?;";


    @Override
    public Notify getRaw(Notify oggetto) throws Exception {
        return null;
    }

    @Override
    public Notify get(Notify oggetto) throws Exception {

        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_NOTIFY_1);

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();
        if(rs == null || rs.next()) throw new RuntimeException("ResultSet is null or empty");

        int id = rs.getInt("id");
        String description = rs.getString("description");


        return new Notify(id, description);

    }

    @Override
    public List<Notify> getAll() throws Exception {
        return List.of();
    }

    @Override
    public void save(Notify notify) throws Exception {

    }

    @Override
    public void update(Notify notify, String[] params) throws Exception {

    }

    @Override
    public void delete(Notify notify) throws Exception {

    }
}
