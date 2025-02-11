package it.unipv.ingsw.lasout.model.group.spesa;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.group.Group;

import java.sql.SQLException;
import java.util.List;

public interface ISpesaDao extends IDao<Spesa> {

    List<Spesa> getGroupSpese(Group group) throws SQLException;

    static SpesaDao getIstance() {
        return null;
    }
}
