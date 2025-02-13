package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public interface ICashbookFacade {

    boolean addCashbook(Cashbook cashbook);

    Cashbook getCashbook(Cashbook cashbook);

    boolean editCashbook(Cashbook cashbook);

    boolean deleteCashbook(Cashbook cashbook);

    boolean addTransaction(Cashbook cashbook, Transaction transaction);

    boolean editTransaction(Cashbook cashbook, Transaction transaction);

    boolean removeTransaction(Cashbook cashbook, Transaction transaction);

    List<Cashbook> getAllCashbooks(User user);

}
