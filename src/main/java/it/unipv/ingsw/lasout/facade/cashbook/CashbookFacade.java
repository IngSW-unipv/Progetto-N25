package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.cashbook.ICashbookDAO;
import it.unipv.ingsw.lasout.model.transaction.ModifiableTransaction;
import it.unipv.ingsw.lasout.model.transaction.Transaction;
import it.unipv.ingsw.lasout.model.transaction.exception.CannotEditTransactionException;
import it.unipv.ingsw.lasout.util.DaoFactory;

import java.util.List;

public class CashbookFacade implements ICashbookFacade {

    private ICashbookDAO cashBookDAO;
    public CashbookFacade() {
        cashBookDAO = DaoFactory.getCashbookDAO();
    }

    private static CashbookFacade instance;
    public static CashbookFacade getInstance() {
        if (instance == null) {
            instance = new CashbookFacade();
        }
        return instance;
    }

    @Override
    public boolean newCashbook(Cashbook cashbook) {
        try {
            cashBookDAO.save(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Cashbook getCashbook(Cashbook cashbook){
        try{
            return cashBookDAO.get(cashbook);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addTransactionToCashbook(Cashbook cashbook, Transaction transaction) {
        try{
            Cashbook c= cashBookDAO.get(cashbook);
            List<Transaction> t = c.getTransactionList();
            t.add(transaction);
            c.setTransactionList(t);
            cashBookDAO.update(c);
        } catch (Exception e) {
            return false;
        }

        return true;
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
    public boolean deleteCashbook(Cashbook cashbook){
        try{
            cashBookDAO.delete(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeTransactionFromCashbook(Cashbook cashbook, Transaction transaction){
        Cashbook c;

        try{
            c=getCashbook(cashbook);
            c.removeTransaction((ModifiableTransaction) transaction);
            cashBookDAO.update(c);
            editCashbook(c);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean editTransactionInCashbook(Cashbook cashbook, Transaction transaction) {
        Cashbook c;
        try{
            c=getCashbook(cashbook);
            c.removeTransaction(transaction);
            cashBookDAO.update(c);
            editCashbook(c);
        } catch (CannotEditTransactionException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
