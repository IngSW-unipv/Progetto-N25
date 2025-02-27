package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.cashbook.exception.CannotDeleteDefaultCashbookException;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.user.User;

import java.util.List;

public interface ICashbookFacade {

    boolean saveCashbook(Cashbook cashbook);

    Cashbook getCashbook(Cashbook cashbook);

    boolean editCashbook(Cashbook cashbook);

    boolean deleteCashbook(Cashbook cashbook) throws CannotDeleteDefaultCashbookException;

    Cashbook getUserDefaultCashbook(User carrierUser);

    boolean addTransaction(Cashbook cashbook, Transaction transaction);

    boolean editTransaction(Cashbook cashbook, Transaction transaction);

    boolean removeTransaction(Cashbook cashbook, Transaction transaction);

    List<Cashbook> getUserCashbooks(User user);

    double calculateSummary(Cashbook cashbook);

	/**
	 * Serve a salvare una transazione conoscendo lo user che la ha eseguita
	 * @param user of which you want to add the transaction
	 * @param transaction transaction to add
	 * @return true if transaction was added correctly, false otherwise
	 */
	boolean addTransaction(User user, Transaction transaction);

    List<Transaction> getAutomaticTransactions(Cashbook c);

    List<Transaction> getVaultTransactionsOfUser(User user);

    Cashbook createDefaultCashbook(User carrierUser);
}
