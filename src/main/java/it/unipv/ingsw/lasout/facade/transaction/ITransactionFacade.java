package it.unipv.ingsw.lasout.facade.transaction;

import it.unipv.ingsw.lasout.model.transaction.Transaction;

import java.util.List;

public interface ITransactionFacade {

    boolean saveTransaction(Transaction transaction);

    Transaction getTransaction(Transaction carrierTransaction);

    boolean editTransaction(Transaction transaction);

    boolean deleteTransaction(Transaction transaction);

}
