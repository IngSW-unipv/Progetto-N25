package it.unipv.ingsw.lasout.facade.cashbook;

import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.cashbook.CashbookDAO;
import it.unipv.ingsw.lasout.model.transaction.Transaction;

import java.util.List;

public class CashbookFacade implements ICashbookFacade {
    private CashbookFacade() {

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
            CashbookDAO.getInstance().save(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Cashbook getCashbook(Cashbook cashbook){
        try{
            return CashbookDAO.getInstance().get(cashbook);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addTransactionToCashbook(Cashbook cashbook, Transaction transaction) {
        try{
            Cashbook c= CashbookDAO.getInstance().get(cashbook);
            List<Transaction> t = c.getTransactionList();
            t.add(transaction);
            c.setTransactionList(t);
            CashbookDAO.getInstance().update(c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean editCashbook(Cashbook cashbook){
        try{
            CashbookDAO.getInstance().update(cashbook);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCashbook(Cashbook cashbook){
        try{
            CashbookDAO.getInstance().delete(cashbook);
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
            if(!transaction.isImmutable()){
                c.removeTransaction(transaction);
            }
            editCashbook(c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
