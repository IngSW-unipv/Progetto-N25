package it.unipv.ingsw.lasout.facade.transaction;

import it.unipv.ingsw.lasout.model.transaction.ITransactionDAO;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.util.DaoFactory;

public class TransactionFacade implements ITransactionFacade {

    ITransactionDAO transactionDAO;
    public TransactionFacade() {
        transactionDAO= DaoFactory.getTransactionDAO();
    }

    private static TransactionFacade instance;
    public static TransactionFacade getInstance() {
        if (instance == null) {
            instance = new TransactionFacade();
        }
        return instance;
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        try {
            transactionDAO.save(transaction);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Transaction getTransaction(Transaction carrierTransaction) {
        try {
            return transactionDAO.get(carrierTransaction);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean editTransaction(Transaction transaction) {
        try {
            transactionDAO.update(transaction);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTransaction(Transaction transaction) {
        try {
            transactionDAO.delete(transaction);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
