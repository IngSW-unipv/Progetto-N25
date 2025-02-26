package it.unipv.ingsw.lasout.model.notify.action.mysql.friend;


import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.user.User;

public class FriendRequestNotifyAction implements INotifyAction {




    /*
    private static final String QUERY_LOAD_1 = "SELECT *" +
            " FROM \\'friendnotify\\'" +
            " WHERE id = ?;";
    private static final String QUERY_DELETE_1 = "DELETE" +
            " FROM \\'friendnotify\\'" +
            " WHERE id = ?;";
    private static final String QUERY_UPDATE = "UPDATE \\'friendnotify\\'" +
            " SET to_user_id = ?, from_user_id = ? WHERE id = ?;";
    private static final String QUERY_SAVE = "INSERT INTO \\'friendnotify\\'" +
            " (id, to_user_id, from_user_id) VALUES (?, ?, ?)";


     */

    private Notify notify;
    private User from;
    private User to;


    public void setFrom(User from) {
        this.from = from;
    }

    public void setTo(User to) {
        this.to = to;
    }



    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }


    /*
    @Override
    public void load(Notify notify) throws Exception{
        DBQuery query = DBQuery.Builder.create()
                .query(QUERY_LOAD_1)
                .params(notify.getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet resultSet = query.getResultSet();
        if(resultSet == null || !resultSet.next()) throw new RuntimeException(String.format("Could not find friend notify  with id '%s' and user_id '%s'", notify.getId(), notify.getUserID()));

        User fromID = UserDAO.getInstance().getRaw(new User(resultSet.getInt("from_user_id")));
        User toID = UserDAO.getInstance().getRaw(new User(resultSet.getInt("to_user_id")));

        this.from = fromID;
        this.to = toID;

        query.close();
    }

    @Override
    public void delete(Notify notify) throws Exception {
        DBQuery dbQuery =  DBQuery.Builder.create()
                .query(QUERY_DELETE_1)
                .params(notify.getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(dbQuery);
        dbQuery.close();
    }

    @Override
    public void save(Notify notify) throws Exception {
        DBQuery dbQuery =  DBQuery.Builder.create()
                .query(QUERY_UPDATE)
                .params(to.getId(),  from.getId(), notify.getId())
                .build();
        DatabaseUtil.getInstance().executeQuery(dbQuery);
        if(dbQuery.getUpdateCount() == 0){
            dbQuery.setQuery(QUERY_SAVE);
            dbQuery.setParams(notify.getId(), to.getId(), from.getId());
            DatabaseUtil.getInstance().executeQuery(dbQuery);
        }
        dbQuery.close();
    }

     */

    @Override
    public void build() {

    }

    @Override
    public Notify getNotify() {
        return notify;
    }

    @Override
    public void setNotify(Notify notify) {
        this.notify = notify;
    }

    @Override
    public String type() {
        return "notifyfriendrequest";
    }

    @Override
    public String toString() {
        return "AcceptFriendRequestNotifyAction{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
