package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public interface INotifyDAO extends IDao<Notify> {

    List<Notify> notifiesOf(User user) throws Exception;

}
