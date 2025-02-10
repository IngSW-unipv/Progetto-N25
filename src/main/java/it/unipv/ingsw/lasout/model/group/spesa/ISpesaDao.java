package it.unipv.ingsw.lasout.model.group.spesa;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.group.Group;

import java.util.List;

public interface ISpesaDao extends IDao<Spesa> {

    List<Spesa> getGroupSpese(Group group) throws Exception;

    static SpesaDao getIstance() {
        return null;
    }
}
