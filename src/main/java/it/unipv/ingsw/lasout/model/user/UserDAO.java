package it.unipv.ingsw.lasout.model.user;

import it.unipv.ingsw.lasout.dao.DBQuery;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.GroupDao;
import it.unipv.ingsw.lasout.model.user.exception.UserNotFoundException;
import it.unipv.ingsw.lasout.util.DatabaseUtil;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class UserDAO implements IUserDAO {

    //ricerca delle informazioni di un utente presente nel dB in base all'ID dell'utente
    @Override
    public User get(User user) throws Exception {

        //creazione della query di ricerca nel DB di tipo "DBQuery"
        DBQuery query = DatabaseUtil.getInstance().createQuery("SELECT *" +
                                                                  "FROM user" +
                                                                  "WHERE id = ?", user.getId());
        //esecuzione della query
        DatabaseUtil.getInstance().executeQuery(query);

        //"resultSet" prende il risultato della query appena fatta
        ResultSet resultSet = query.getResultSet();
        //se la query non da risultati o non c'è niente dopo c'è dopo il primo carattere allora viene lanciata l'eccezione
        if(resultSet == null || !resultSet.next()) throw new UserNotFoundException("User not found");

        //creazione di un bean in cui metto l'id preso dalla query
        User savedUser = new User();
        savedUser.setId(resultSet.getInt("id"));
        //qui volendo ci sarebbe già la chiusura del metodo





        //DA QUI IN POI COSA FA?
        query.setQuery(
                "SELECT *" +
                "FROM relgroupuser" +
                "WHERE user_id = ?");
        query.setParams(savedUser.getId());

        DatabaseUtil.getInstance().executeQuery(query);
        resultSet = query.getResultSet();
        if(resultSet == null) throw new RuntimeException("user not found");

        while(resultSet.next()) {

            Group group = GroupDao.getInstance().get(new Group(resultSet.getInt("group_id")));
            savedUser.getGroups().add(group);

        }


        query.close();
        return savedUser;


    }

    //ricerca delle informazioni di un utente presente nel dB
    @Override
    public List<User> getAll() throws Exception {
        //creazione della querySelectAll di ricerca nel DB di tipo "DBQuery"
        DBQuery querySelectAll = DatabaseUtil.getInstance().createQuery("SELECT *" +
                                                                           "FROM user" );
        //esecuzione della querySelectAll
        DatabaseUtil.getInstance().executeQuery(querySelectAll);

        //"resultSet" prende il risultato della querySelectAll appena fatta
        ResultSet resultSet = querySelectAll.getResultSet();
        //se la querySelectAll non da risultati o non c'è niente dopo c'è dopo il primo carattere allora viene lanciata l'eccezione
        if(resultSet == null || !resultSet.next()) throw new UserNotFoundException("User not found");

        //creazione di un bean in cui metto l'id preso dalla querySelectAll
        User savedUser = new User();
        savedUser.setId(resultSet.getInt("id"));

        return List.of();
    }

    //inserimento del nuovo utente nel dB
    @Override
    public void save(User user) throws Exception {
        //TODO capire come fare l'auto increment dell'id utente (credo cmq sia una cosa da fare sulla tabella del DB con: id INT AUTO_INCREMENT PRIMARY KEY)
        String iD, username, password;


        //dema sono cla che stai a fare con uno scanner system in??? qui stiamo idealizando niente fa nulla e sopratutto non lo facciamo dalla console
        //interazione con l'utente per l'inserimento
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me the username:");
        username= scanner.nextLine();
        System.out.println("Give me the password");
        password = scanner.nextLine();

        //query di inserimento di un nuovo user
        DBQuery queryInsert = DatabaseUtil.getInstance().createQuery("INSERT INTO user(?, ?)", username, password);
        //esecuzione della queryInsert
        DatabaseUtil.getInstance().executeQuery(queryInsert);

        //TODO controlli vari per la corretta esecuzione della query
        queryInsert.close();
    }

    //modifica delle informazioni di un utente presente nel dB
    @Override
    public void update(User user, String[] params) throws Exception {

    }

    //eliminazione di un utente presente nel dB
    @Override
    public void delete(User user) throws Exception {

    }
}
