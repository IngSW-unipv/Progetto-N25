package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.transaction.Transaction;

public interface ICashbookFacade {
    boolean newCashbook(Cashbook cashbook);

    Cashbook getCashbook(Cashbook cashbook);

    boolean deleteCashbook(Cashbook cashbook);

    boolean addTransactionToCashbook(Cashbook cashbook, Transaction transaction);

    boolean editCashbook(Cashbook cashbook);

    boolean removeTransactionFromCashbook(Cashbook cashbook, Transaction transaction);

    boolean editTransactionInCashbook(Cashbook cashbook, Transaction transaction);

}
