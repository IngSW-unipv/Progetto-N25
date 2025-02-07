package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.factory.AbstractNotifyActionFactory;
import it.unipv.ingsw.lasout.model.notify.factory.EmptyNotifyActionFactory;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Logger;

public class NotifyDAO implements IDao<Notify> {

    private static final Logger LOGGER = Logger.getLogger(NotifyDAO.class.getName());
    private static final NotifyDAO INSTANCE = new NotifyDAO();
    public static NotifyDAO getInstance() {
        return INSTANCE;
    }

    private final Map<String, Class<?>> classes = new HashMap<>();

    private NotifyDAO() {

        populateMap();

    }

    private static final String QUERY_GET_RAW_NOTIFY_1 =
            "SELECT *" +
                    "FROM \\'notify\\' " +
                    "WHERE user_id = ? AND id = ?;";
    private static final String QUERY_GET_ALL_NOTIFY_1 =
            "SELECT *" +
                    "FROM \\'notify\\';";

    private static final String SELECT_TYPE =
            "SELECT \\'type\\'" +
                    "FROM \\'notify\\' WHERE user_id = ? AND id = ?;";


    private static final String QUERY_SAVE_NOTIFY_1 =
            "INSERT INTO \\'notify\\' " +
                    "(user_id, id, description, type) VALUES (?, ?, ?, ?);";
    private static final String QUERY_UPDATE_NOTIFY_1 =
            "UPDATE \\'notify\\' " +
                    "SET user_id = ?, id = ?, \\'description\\' = ?, \\'type\\' = ? WHERE user_id = ? AND id = ?;";



    private void populateMap(){
        Properties properties = new Properties();
        try {
            properties.load(NotifyDAO.class.getResourceAsStream("/factories.properties"));
        } catch (IOException e) {
            LOGGER.severe("Could not load notify factories");
        }
        properties.forEach((key, value) -> {
            try {
                classes.put((String) key, Class.forName(value.toString()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }


    private Class<?> getClass(String key){
        return classes.getOrDefault(key, EmptyNotifyActionFactory.class);
    }

    @Override
    public Notify getRaw(Notify notify) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GET_RAW_NOTIFY_1, notify.getUserID(), notify.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();
        if(rs == null || !rs.next()) throw new RuntimeException("ResultSet is null or empty");

        Long id = notify.getId();
        User user = UserDAO.getInstance().get(new User(notify.getUserID()));
        String description = rs.getString("description");

        String type = rs.getString("type");
        Class<?> clazz = getClass(type);
        AbstractNotifyActionFactory factory = (AbstractNotifyActionFactory) clazz.getDeclaredConstructor().newInstance();
        INotifyAction iiNotifyAction =  factory.create();

        Notify returnNotify  = new Notify(id, user);
        returnNotify.setDescription(description);

        iiNotifyAction.load(returnNotify);
        returnNotify.setNotifyAction(iiNotifyAction);

        query.close();
        return returnNotify;
    }



    @Override
    public Notify get(Notify oggetto) throws Exception {

        return getRaw(oggetto);

    }

    @Override
    public List<Notify> getAll() throws Exception {
        DBQuery query = DBQuery.Builder.create()
                .query(QUERY_GET_ALL_NOTIFY_1)
                .build();
        DatabaseUtil.getInstance().executeQuery(query);

        List<Notify> all = new ArrayList<Notify>();

        ResultSet rs = query.getResultSet();
        while(rs.next()){
            Long id = rs.getLong("id");
            User user = new User(rs.getInt("user_id"));
            Notify notify = get(new Notify(id, user));
            all.add(notify);
        }

        query.close();

        return all;
    }

    @Override
    public void save(Notify notify) throws Exception {
        DBQuery update = DBQuery.Builder.create()
                .query(QUERY_UPDATE_NOTIFY_1)
                .params(notify.getUserID(), notify.getId(), notify.getDescription(), notify.getNotifyType(), notify.getUserID(), notify.getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(update);

        if(update.getUpdateCount() > 0){
            //update.setQuery(DELETE_TYPE);
            update.close();
        }

        update.close();
    }

    @Override
    public void update(Notify notify) throws Exception {

    }


    @Override
    public void delete(Notify notify) throws Exception {

    }
}
