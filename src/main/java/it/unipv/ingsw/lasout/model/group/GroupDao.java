package it.unipv.ingsw.lasout.model.group;


import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.database.DatabaseUtil;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.notify.NotifyFacade;
import it.unipv.ingsw.lasout.model.group.exception.CantDeleteException;
import it.unipv.ingsw.lasout.model.group.exception.CantSaveException;
import it.unipv.ingsw.lasout.model.group.exception.NoResoultException;
import it.unipv.ingsw.lasout.model.group.spesa.ISpesaDao;
import it.unipv.ingsw.lasout.model.group.spesa.Spesa;
import it.unipv.ingsw.lasout.model.group.spesa.SpesaDao;
import it.unipv.ingsw.lasout.model.user.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GroupDao implements IGroupDao {

    private ISpesaDao spesaDao;

    /**
     * Istanza singola del GroupDao (implementazione singleton)
     */
    private static GroupDao instance = null;

    /**
     * @return l'istanza singleton del GroupDao
     */
    public static GroupDao getInstance() {
        if (instance == null) {
            instance = new GroupDao();
        }
        return instance;
    }

    /**
     * Rendo il costruttore privato e creo un istanza con un factory di ISpesaDao
     */
    public GroupDao()  {
        try {
            Properties properties  = new Properties();
            properties.load(LaVaultFacade.class.getResourceAsStream("/app.properties"));
            this.spesaDao = (ISpesaDao) Class.forName(properties.getProperty("dao.spesa")).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("la droga fa male, ma male male "+e.getMessage());
            throw new RuntimeException(e);

        }
    }


    private static final String GET_GROUP_FROM_ID = "SELECT * FROM £group£ WHERE id = ?";
    private static final String GAT_ALL_GROUP = "SELECT * FROM £group£";
    private static final String INSERT_GROUP_NOID = "INSERT INTO £group£ (name, user_id) VALUES(?,?)";
    private static final String INSERT_GROUP_ID = "INSERT INTO £group£ (id, name, user_id) VALUES(?,?,?)";
    private static final String DELATE_GROUP_FROM_ID = "DELETE FROM £group£ WHERE id = ?";
    private static final String GET_USER_FROM_USERGROUP = "SELECT * FROM £usergroup£ WHERE group_id = ?";
    private static final String DELATE_ASSO_FROM_USERGROUP = "DELETE FROM £usergroup£ WHERE group_id = ?";
    private static final String INSERT_ASSO_FROM_USERGROUP = "INSERT INTO £usergroup£ (user_id, group_id) VALUES(?,?)";

    /**
     * Ricerca di un gruppo sul db tramite l'id del gruppo
     *
     * @param fictitiousGroup L'id ricercato
     * @return Oggetto completo del gruppo ricercato
     * @throws Exception non trovato gruppo con l'id inserito
     */
    @Override
    public Group get(Group fictitiousGroup) throws NoResoultException, SQLException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_GROUP_FROM_ID, fictitiousGroup.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (!rs.next()) throw new NoResoultException("no resoult found");

        //creazione del pojo
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        group.setAdmin(new User(rs.getInt("user_id")));
        group.setMembers(members(new Group(rs.getInt("id"))));
        group.setSpese(spesaDao.getGroupSpese(new Group(rs.getInt("id"))));

        query.close();
        return group;
    }

    /**
     * @return Lista di tutti i gruppi presenti nel database
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    @Override
    public List<Group> getAll() throws SQLException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GAT_ALL_GROUP);
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        List<Group> groups = new ArrayList<Group>();
        while (rs.next()) {
            Group group = new Group();
            group.setId(rs.getInt("id"));
            group.setName(rs.getString("name"));
            group.setAdmin(new User(rs.getInt("admin")));
            group.setMembers(members(new Group(rs.getInt("id"))));
            group.setSpese(spesaDao.getGroupSpese(new Group(rs.getInt("id"))));
            groups.add(group);
        }

        query.close();
        return groups;
    }

    /**
     * @param fictitiousGroup ogetto contenente il solo identificatore dell'entità
     * @return un pojo group contenente solo le informazioni raw
     * @throws Exception Errore nel esecuzione della query o mancata connesioine al db
     */
    public Group getRaw(Group fictitiousGroup) throws NoResoultException, SQLException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_GROUP_FROM_ID, fictitiousGroup.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (!rs.next()) throw new NoResoultException("no resoult found");
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        group.setAdmin(new User(rs.getInt("user_id")));

        query.close();
        return group;
    }

    /**
     * Salva l'oggetto gurppo sul database tennendo conto del opzione
     * "aggiornamento" id=0, e delle relazioni many to many
     *
     * @param group pojo di un gruppo che si vuole scrivere sul database
     *              se l'id non e insereito si creera un nuova tupla
     * @throws Exception errore nella esecuzione della query
     *                   o errore nella generazione della chiave
     */
    @Override
    public void save(Group group) throws Exception {
        DBQuery query;
        if (group.getId() != 0) {
            //usa l'id del carry (praticmante
            query = DatabaseUtil.getInstance().createQuery(INSERT_GROUP_ID, group.getId(), group.getName(), group.getAdmin().getId());
        } else {
            //sfrutta auto increment
            query = DatabaseUtil.getInstance().createGeneratedKeyQuery(INSERT_GROUP_NOID, group.getName(), group.getAdmin().getId());
        }
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (rs != null) throw new CantSaveException("Group not saved");
        if (group.getId() == 0) group.setId(query.getKey());
        //INSERT nella tabella usergroup
        saveAssociation(group);
        for (int i = 0; group.getSpese().size() > i; i++) {
            spesaDao.save(group.getSpese().get(i));
        }
        query.close();
    }

    /**
     * @param group pojo con i dati da scrivere sul db con id l'id del gruppo da aggiornare
     * @throws Exception error in sql execute
     */
    @Override
    public void update(Group group) throws Exception {
        delete(group);
        save(group);
    }

    /**
     * Eliminazione di un gruppo dal database per agiornamento o eliminazione dei
     * dati tenendo conto anche delle relazioni ed eliminandole di conseguenza
     *
     * @param group carry group contentente solo l'id del gruppo da eliminare dal database
     * @throws Exception errore nel esequzione della query sql
     */
    @Override
    public void delete(Group group) throws CantDeleteException, SQLException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELATE_GROUP_FROM_ID, group.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (rs != null) throw new CantDeleteException("Group not deleted");
        deleteAssociation(group);
        query.close();
    }


    /**
     * UserGroupDAO: retituisce la lista bi user di un gruppo dalla relazione usergroup (many to many)
     *
     * @param group carry group contentente solo l'id del gruppo di cui si vogliono i membri
     * @return Lista di user con solo il loro id (per evitare ricorsione e loop)
     * @throws Exception Errore nel esecuzione della query sql
     */
    @Override
    public List<User> members(Group group) throws SQLException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(GET_USER_FROM_USERGROUP, group.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        //lista di pojo
        List<User> user = new ArrayList<User>();
        while (rs.next()) {
            //ogni volta un nuovo pojo
            User u = new User(rs.getInt("user_id"));
            user.add(u);
        }
        query.close();
        return user;
    }

    /**
     * UserGroupDAO: cancella tutte le tuple di un asociazione molti molti dato l'id del gruppo
     *
     * @param group id del gurppo di cui si vogliono eliminare le associazioni
     * @throws Exception errore nel esequzione della query sql
     *                   Sostituiblie da un delete on cascate
     */
    @Override
    public void deleteAssociation(Group group) throws CantSaveException, SQLException {
        DBQuery query = DatabaseUtil.getInstance().createQuery(DELATE_ASSO_FROM_USERGROUP, group.getId());
        DatabaseUtil.getInstance().executeQuery(query);
        ResultSet rs = query.getResultSet();

        if (rs != null) throw new CantDeleteException("can't delete assocation");
        query.close();
    }

    /**
     * UserGroupDAO: crea le associazioni many to many dato l'id del gruppo e la lista delli user
     *
     * @param group carry con l'id del gruppo e la lista delli user per creare l'associazioni many to many
     * @throws Exception errore nel esequzione della query sql
     */
    @Override
    public void saveAssociation(Group group) throws Exception {
        DBQuery query = null;
        for (User u : group.getMembers()) {
            query = DatabaseUtil.getInstance().createQuery(INSERT_ASSO_FROM_USERGROUP, u.getId(), group.getId());
            DatabaseUtil.getInstance().executeQuery(query);
            ResultSet rs = query.getResultSet();
            if (rs != null) throw new CantSaveException("can't save association");
        }
        if (query != null) query.close();
    }

    public ISpesaDao getSpesaDao() {
        return spesaDao;
    }

    public void setSpesaDao(ISpesaDao spesaDao) {
        this.spesaDao = spesaDao;
    }
}

