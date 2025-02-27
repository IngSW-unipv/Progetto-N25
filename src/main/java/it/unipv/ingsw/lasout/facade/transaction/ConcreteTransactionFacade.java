package it.unipv.ingsw.lasout.facade.transaction;

import it.unipv.ingsw.lasout.model.transaction.ITransactionDAO;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotDeleteAutomaticTransactionException;
import it.unipv.ingsw.lasout.util.DaoFactory;

public class ConcreteTransactionFacade implements ITransactionFacade {

    ITransactionDAO transactionDAO;
    public ConcreteTransactionFacade() {
        transactionDAO = DaoFactory.getTransactionDAO();
    }

    private static ConcreteTransactionFacade instance;
    public static ConcreteTransactionFacade getInstance() {
        if (instance == null) {
            instance = new ConcreteTransactionFacade();
        }
        return instance;
    }

    @Override
    public boolean saveTransaction(Transaction transaction) {
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
            throw new RuntimeException(e);
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
            throw new CannotDeleteAutomaticTransactionException();
        }
        return true;
    }

}
