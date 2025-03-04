package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.cashbook.ICashbookDAO;
import it.unipv.ingsw.lasout.model.cashbook.exception.CannotDeleteDefaultCashbookException;
import it.unipv.ingsw.lasout.model.cashbook.exception.CashbookAlreadyExistingException;
import it.unipv.ingsw.lasout.model.transaction.AutomaticTransaction;
import it.unipv.ingsw.lasout.model.transaction.ModifiableTransaction;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotDeleteAutomaticTransactionException;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotEditTransactionException;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.util.ArrayList;
import java.util.List;

public class ConcreteCashbookFacade implements ICashbookFacade {

    private ICashbookDAO cashBookDAO;
    public ConcreteCashbookFacade() {
        cashBookDAO = DaoFactory.getCashbookDAO();
    }

    private static ConcreteCashbookFacade instance;
    public static ConcreteCashbookFacade getInstance() {
        if (instance == null) {
            instance = new ConcreteCashbookFacade();
        }
        return instance;
    }

    @Override
    public boolean saveCashbook(Cashbook cashbook) throws CashbookAlreadyExistingException{
        if(checkAlreadyExist(cashbook)){
            throw new CashbookAlreadyExistingException(cashbook.getName());
        }

        try {
            cashBookDAO.save(cashbook);
        } catch (CashbookAlreadyExistingException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Cashbook getCashbook(Cashbook carrierCashbook){
        try{
            return cashBookDAO.get(carrierCashbook);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean editCashbook(Cashbook cashbook){
        try{
            cashBookDAO.update(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCashbook(Cashbook cashbook) throws CannotDeleteDefaultCashbookException {
        try {
            // verifico se il cashbook non è default
            if (!cashBookDAO.getRaw(cashbook).isDefault()) {
                cashBookDAO.delete(cashbook);
                return true;
            } else {
                throw new CannotDeleteDefaultCashbookException();
            }
        } catch (CannotDeleteDefaultCashbookException ex) {
            // propago
            throw ex;
        } catch (Exception e) {
            // altre eccezioni
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Cashbook> getUserCashbooks(User carrierUser){
        try{
            return cashBookDAO.getAllUserCashbooks(carrierUser);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cashbook getUserDefaultCashbook(User carrierUser){
        try{
            return cashBookDAO.getDefaultCashbook(carrierUser);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addTransaction(Cashbook cashbook, Transaction transaction) {
        try{
            cashBookDAO.addTransaction(cashbook, transaction);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean editTransaction(Cashbook cashbook, Transaction transaction) {
        try {
            LaVaultFacade.getInstance().getTransactionFacade().editTransaction(transaction);
            return true;
        }catch (CannotEditTransactionException e) {
            throw e;
        }
    }

    /**
     * Serve a salvare una transazione conoscendo lo user che la ha eseguita
     * @param user of which you want to add the transaction
     * @param transaction transaction to add
     * @return true if transaction was added correctly, false otherwise
     */
    @Override
    public boolean addTransaction(User user, Transaction transaction) {
        Cashbook defaultCashbook = cashBookDAO.getDefaultCashbook(user);
        return addTransaction(defaultCashbook, transaction);
    }

    @Override
    public boolean removeTransaction(Cashbook cashbook, Transaction transaction){
        try{
            Cashbook c = getCashbook(cashbook);
            c.removeTransaction((ModifiableTransaction) transaction);
            LaVaultFacade.getInstance().getTransactionFacade().deleteTransaction(transaction);
        } catch (Exception e) {
            throw new CannotDeleteAutomaticTransactionException();
        }
        return true;
    }

    @Override
    public double calculateSummary(Cashbook cashbook){
        double sum = 0;
        try {
            List<Transaction> transactionList = cashBookDAO.get(cashbook).getTransactionList();
            if (transactionList != null) {
                for (Transaction transaction : transactionList) {
                    sum += transaction.getAmount();
                }
            }
            return sum;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getAutomaticTransactions(Cashbook c){
        List<Transaction> transactionList = c.getTransactionList();
        List<Transaction> automaticTransactions = new ArrayList<Transaction>();

        for(Transaction transaction : transactionList){
            try{
                AutomaticTransaction automaticTransaction = (AutomaticTransaction) transaction;
                automaticTransactions.add(automaticTransaction);
            } catch (Exception e) {
                continue;
            }
        }

        return automaticTransactions;
    }

    @Override
    public List<Transaction> getVaultTransactionsOfUser(User user){
        Cashbook c = getUserDefaultCashbook(user);
        return getAutomaticTransactions(c);
    }

    private boolean checkAlreadyExist(Cashbook cashbook){
        List<Cashbook> l=getUserCashbooks(cashbook.getUser());
        for(Cashbook c : l){
            if(c.getName().equals(cashbook.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Cashbook createDefaultCashbook(User carrierUser) {
        Cashbook defaultCashbook = new Cashbook(carrierUser, "default", true);
        saveCashbook(defaultCashbook);

        return defaultCashbook;
    }
}
