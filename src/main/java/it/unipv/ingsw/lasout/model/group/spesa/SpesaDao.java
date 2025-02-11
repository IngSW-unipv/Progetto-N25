package it.unipv.ingsw.lasout.model.group.spesa;

import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpesaDao implements ISpesaDao {

    private static final String GET_SPESA_FROMID = "select * from £spese£ where id=?";
    private static final String INSERT_SPESA_ID = "INSERT INTO £spese£ (id, user_id, group_id, amount, note) VALUES (?,?,?,?,?) ";
    private static final String INSERT_SPESA_NOID = "INSERT INTO £spese£ ( user_id, group_id, amount, note) VALUES (?,?,?,?) ";
    private static final String DELATE_SPESA_FORM_ID = "DELETE FROM £spese£ WHERE group_id = ?";
    private static final String GET_SPESE_FORM_GROUP = "SELECT * FROM £spese£ WHERE group_id = ?";

    public static SpesaDao INSTANCE = null;

    public static SpesaDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpesaDao();
        }
        return INSTANCE;
    }

    private SpesaDao() {
        super();
    }


    @Override
    public Spesa getRaw(Spesa oggetto) throws Exception {
        return (oggetto);
    }

    @Override
    public Spesa get(Spesa spesa) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_SPESA_FROMID, spesa.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (!rs.next()) throw new Exception();
        Spesa s = new Spesa();
        s.setId(rs.getInt(1));
        s.setEsecutore(new User(rs.getInt(2)));
        s.setGroup((new Group(rs.getInt(3))));
        s.setAmount(rs.getDouble(4));
        s.setNote(rs.getString(5));

        query.close();
        return s;
    }

    @Override
    public List<Spesa> getAll() throws Exception {
        return List.of();
    }

    @Override
    public void save(Spesa spesa) throws Exception {
        DBQuery query;
        if (spesa.getId() != 0) {
            query = DatabaseUtil.getInstance().createQuery(INSERT_SPESA_ID, spesa.getId(), spesa.getEsecutore().getId(), spesa.getGroup().getId(), spesa.getAmount(), spesa.getNote());
        } else {
            query = DatabaseUtil.getInstance().createGeneratedKeyQuery(INSERT_SPESA_NOID, spesa.getEsecutore().getId(), spesa.getGroup().getId(), spesa.getAmount(), spesa.getNote());
        }
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (rs != null) throw new Exception();
        query.close();
    }

    @Override
    public void update(Spesa spesa) throws Exception {
        delete(spesa);
        save(spesa);
    }

    @Override
    public void delete(Spesa spesa) throws Exception {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELATE_SPESA_FORM_ID, spesa.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (rs != null) throw new Exception();
        query.close();
    }

    @Override
    public List<Spesa> getGroupSpese(Group group) throws SQLException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_SPESE_FORM_GROUP, group.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        List<Spesa> spese = new ArrayList<Spesa>();
        while (rs.next()) {
            Spesa s = new Spesa();
            s.setId(rs.getInt(1));
            s.setEsecutore(new User(rs.getInt(2)));
            s.setGroup((new Group(rs.getInt(3))));
            s.setAmount(rs.getDouble(4));
            s.setNote(rs.getString(5));
            spese.add(s);
        }
        query.close();
        return spese;
    }
}
