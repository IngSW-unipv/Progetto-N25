package it.unipv.ingsw.lasout.model.cashbook;

import it.unipv.ingsw.lasout.model.transaction.Transaction;

import java.util.List;

public class CashbookFacade {
    private CashbookFacade() {

    }
    private static CashbookFacade instance;
    public static CashbookFacade getInstance() {
        if (instance == null) {
            instance = new CashbookFacade();
        }
        return instance;
    }


    public boolean addTransaction(Cashbook cashbook, Transaction transaction) {
        try{
            Cashbook c=CashbookDAO.getInstance().get(cashbook);
            List<Transaction> t = c.getTransactionList();
            t.add(transaction);
            c.setTransactionList(t);
            CashbookDAO.getInstance().update(c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}
