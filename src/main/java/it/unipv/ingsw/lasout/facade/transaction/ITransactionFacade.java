package it.unipv.ingsw.lasout.facade.transaction;

import it.unipv.ingsw.lasout.model.transaction.Transaction;

import java.util.List;

public interface ITransactionFacade {
    boolean newTransaction(Transaction transaction);

    boolean deleteTransaction(Transaction transaction);
    boolean updateTransaction(Transaction transaction);
    Transaction getTransaction(Transaction carrierTransaction);
    List<Transaction> getAllTransactions();

}
