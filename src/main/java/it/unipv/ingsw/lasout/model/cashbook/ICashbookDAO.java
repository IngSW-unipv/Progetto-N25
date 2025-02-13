package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public interface ICashbookDAO extends IDao<Cashbook> {

    List<Cashbook> getAllUserCashbooks(User carrierUser) throws Exception;
}
