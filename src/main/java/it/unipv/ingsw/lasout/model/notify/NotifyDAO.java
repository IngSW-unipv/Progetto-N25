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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

    private static final String QUERY_NOTIFY_1 =
            "SELECT  *" +
                    "FROM 'notify' NATURAL JOIN 'notifytype'" +
                    "WHERE user_id = ? AND id = ?;";


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
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_NOTIFY_1, notify.getUserID(), notify.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();
        if(rs == null || rs.next()) throw new RuntimeException("ResultSet is null or empty");

        int id = notify.getId();
        User user = UserDAO.getInstance().get(new User(notify.getUserID()));
        String description = rs.getString("description");

        String type = rs.getString("type");
        Class<?> clazz = getClass(type);
        AbstractNotifyActionFactory factory = (AbstractNotifyActionFactory) clazz.getDeclaredConstructor().newInstance();
        INotifyAction iiNotifyAction =  factory.create();

        Notify returnNotify  = new Notify(id, user);
        returnNotify.setDescription(description);
        returnNotify.setNotifyAction(iiNotifyAction);
        iiNotifyAction.load(returnNotify);

        return notify;
    }



    @Override
    public Notify get(Notify oggetto) throws Exception {

        return getRaw(oggetto);

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
