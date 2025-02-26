package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistence;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistenceFactory;
import it.unipv.ingsw.lasout.model.notify.action.mysql.MYSQLNotifyActionPersistenceFactory;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.user.UserDAO;

import java.sql.ResultSet;
import java.util.*;

public class MySQLNotifyDAO implements INotifyDAO {


    private static final String  DELETE_FROM_NOTIFY_1=
            "DELETE FROM \\'notify\\' " +
                    "WHERE  id = ?;";

    private static final String QUERY_GET_RAW_NOTIFY_1 =
            "SELECT *" +
                    "FROM \\'notify\\' " +
                    "WHERE id = ?;";
    private static final String QUERY_GET_ALL_NOTIFY_1 =
            "SELECT *" +
                    "FROM \\'notify\\';";
    private static final String SELECT_TYPE =
            "SELECT \\'type\\'" +
                    "FROM \\'notify\\' WHERE id = ?;";
    private static final String QUERY_SAVE_NOTIFY_1 =
            "INSERT INTO \\'notify\\' " +
                    "(user_id, description, type) VALUES (?, ?, ?, ?);";
    private static final String QUERY_SAVE_UNKNOWN_NOTIFY_1 =
            "INSERT INTO \\'notify\\' " +
                    "(user_id, description, type) VALUES (?, ?, ?);";
    private static final String QUERY_UPDATE_NOTIFY_1 =
            "UPDATE \\'notify\\' " +
                    "SET user_id = ?, id = ?, \\'description\\' = ?, \\'type\\' = ? WHERE id = ?;";
    private static final String QUERY_GET_NOTIFIES_OF_USER =
            "SELECT *" +
                    "FROM \\'notify\\' " +
                    "WHERE user_id = ?;";



    private INotifyActionPersistenceFactory persistenceFactory;

    private static final Map<String, Class<?>> classes = new HashMap<>();


    public MySQLNotifyDAO() {
        persistenceFactory = new MYSQLNotifyActionPersistenceFactory();
    }






    @Override
    public Notify getRaw(Notify notify) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(QUERY_GET_RAW_NOTIFY_1, notify.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();
        if(rs == null || !rs.next()) throw new RuntimeException("ResultSet is null or empty");

        Long id = notify.getId();
        User user = UserDAO.getInstance().getRaw(new User(rs.getInt("user_id")));
        String description = rs.getString("description");

        String type = rs.getString("type");

        /*
        Class<?> clazz = getFactory(type);
        AbstractNotifyActionFactory factory = (AbstractNotifyActionFactory) clazz.getDeclaredConstructor().newInstance();
        INotifyAction iiNotifyAction =  factory.create();

         */

        INotifyAction iiNotifyAction = NotifyActionFactory.get(type);

        Notify returnNotify  = new Notify(id);
        returnNotify.setUser(user);
        returnNotify.setDescription(description);
        //iiNotifyAction.setNotify(returnNotify);

        INotifyActionPersistence actionPersistence = getPersistenceFactory().getPersistence(type);

        System.out.println("loading: " + type);
        System.out.println("with: " + actionPersistence);
        //actionPersistence.load(iiNotifyAction);

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
            Notify notify = get(new Notify(id));
            all.add(notify);
        }

        query.close();

        return all;
    }

    public List<Notify> notifiesOf(User user) throws Exception {

        DBQuery dbQuery = DBQuery.Builder.create()
                .query(QUERY_GET_NOTIFIES_OF_USER)
                .params(user.getId())
                .build();

        DatabaseUtil.getInstance().executeQuery(dbQuery);
        ResultSet resultSet = dbQuery.getResultSet();

        if(resultSet == null) throw new RuntimeException("ResultSet is null or empty");

        int i = 0;

        List<Notify> notifies = new ArrayList<>();
        while(resultSet.next()){

            long id = resultSet.getLong("id");
            Notify notify = get(new Notify(id));
            notifies.add(notify);
            i++;
        }



        return notifies;

    }

    @Override
    public INotifyActionPersistenceFactory getPersistenceFactory() {
        return persistenceFactory;
    }

    /**
     * Gestisce automaticamente l'update di un oggetto in memoria o il salvataggio nuovo.
     * @param notify oggetto da salvare, con  tutte le informazioni
     * @throws Exception
     */
    @Override
    public void save(Notify notify) throws Exception {

        if(notify.getId() != null){
            DBQuery query =  DBQuery.Builder.create()
                    .query(SELECT_TYPE)
                    .params( notify.getId())
                    .build();

            try{
                update(notify, query);
                System.out.println("UPDATED");
                query.close();
                return;
            }catch (Exception e){
                query.setQuery(QUERY_SAVE_NOTIFY_1);
                query.setParams(notify.getUserID(), notify.getId(),  notify.getDescription(), notify.getNotifyType());
                DatabaseUtil.getInstance().executeQuery(query);

                INotifyActionPersistence actionPersistence = getPersistenceFactory().getPersistence(notify.getNotifyType());
                actionPersistence.save(notify);

                System.out.println("SAVED");
                query.close();
            }
            return;
        }

        DBQuery query = DBQuery.Builder.create()
                .query(QUERY_SAVE_UNKNOWN_NOTIFY_1)
                .getGeneratedKeys()
                .params(notify.getUserID(), notify.getDescription(), notify.getNotifyType())
                .build();
        DatabaseUtil.getInstance().executeQuery(query);

        System.out.println(query.getKey());

        Long newID = query.getKey();
        notify.setId(newID);

        INotifyActionPersistence actionPersistence = getPersistenceFactory().getPersistence(notify.getNotifyType());
        actionPersistence.save(notify);

        System.out.println("SAVED NEW");


        query.close();






    }

    private void update(Notify notify, DBQuery query) throws Exception{

        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet resultSet  =  query.getResultSet();

        if(resultSet == null || !resultSet.next()) throw new RuntimeException("ResultSet is null or empty");

        String type = resultSet.getString("type");
        query.setQuery(QUERY_UPDATE_NOTIFY_1);
        query.setParams(notify.getUserID(), notify.getId(), notify.getDescription(), notify.getNotifyType(),  notify.getId());
        DatabaseUtil.getInstance().executeQuery(query);

        INotifyActionPersistence actionPersistence = getPersistenceFactory().getPersistence(type);
        actionPersistence.delete(notify);

        actionPersistence = getPersistenceFactory().getPersistence(notify.getNotifyType());
        actionPersistence.save(notify);
    }

    @Override
    public void update(Notify notify) throws Exception {

        update(notify, DBQuery.Builder.create()
                .query(SELECT_TYPE)
                .params(notify.getId())
                .build());

    }

    @Override
    public void delete(Notify notify) throws Exception {

        INotifyActionPersistence actionPersistence = getPersistenceFactory().getPersistence(notify.getNotifyType());
        actionPersistence.delete(notify);

        DBQuery dbQuery = DBQuery.Builder.create().query(DELETE_FROM_NOTIFY_1).params(notify.getId()).build();
        DatabaseUtil.getInstance().executeQuery(dbQuery);
    }


}
